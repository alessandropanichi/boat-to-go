package logic.controller;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Control;
import javafx.scene.control.Alert.AlertType;
import logic.Main;
import logic.bean.CityDateBean;
import logic.bean.BoatShopBean;
import logic.model.BookBoatShopController;
import logic.view.BookBoatShopListView;

/**
 *         MVC controller of the view BookBoatShopListView.
 */
public class BookBoatShopListViewController extends MainViewController {

	/**
	 * Reference to the view.
	 */
	private BookBoatShopListView bookBoatShopListView;

	/**
	 * Reference to the bean, that will contain the input of the user.
	 */
	private CityDateBean fields;

	/**
	 * Constructor of the class. It initialize the state of the object and
	 * initialize the view with the data of the model.
	 * 
	 * @param view   the view.
	 * @param model  the model.
	 * @param fields bean, input of the user.
	 */
	public BookBoatShopListViewController(BookBoatShopListView view, BookBoatShopController model, CityDateBean fields) {
		super(view, model);

		this.bookBoatShopListView = (BookBoatShopListView) super.view;
		this.fields = fields;

		/* Add handlers to buttons. */
		this.bookBoatShopListView.addSearchListener(new SearchHandler());
		this.bookBoatShopListView.addMinusHanlder(new MinusHandler());
		this.bookBoatShopListView.addPlusHanlder(new PlusHandler());

		/* Set the text fields with the input provided by the user. */
		this.bookBoatShopListView.setCityField(this.fields.getCity());
		this.bookBoatShopListView.setCheckInDate(this.fields.getCheckIn());
		this.bookBoatShopListView.setCheckOutDate(this.fields.getCheckOut());
		this.bookBoatShopListView.setPersonCountText(String.valueOf(this.fields.getPersonCount()));

		if (Integer.parseInt(this.bookBoatShopListView.getPersonCount()) == 0)

			/* The minus button has to be disabled */
			this.bookBoatShopListView.disableMinusButton();

		List<BoatShopBean> boatShops = new ArrayList<>();
		boatShops = this.model.retrieveBoatShopByCity(this.fields.getCity());

		if (boatShops.isEmpty())

			/* The input of the doesn't produce result. */
			this.bookBoatShopListView.resultNotFound();

		else

			/* Set the data found to the view. */
			this.bookBoatShopListView.populateView(boatShops, new MoreInformationHandler());

	}

	/**
	 *         This class implements the EventHandler interface providing the handle
	 *         method for minus button.
	 */
	private class MinusHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {

			int personCount = Integer.parseInt(bookBoatShopListView.getPersonCount());
			personCount--;
			if (personCount == 0)
				bookBoatShopListView.disableMinusButton();
			bookBoatShopListView.setPersonCountText(String.valueOf(personCount));

		}

	}

	/**
	 *         This class implements the EventHandler interface providing the handle
	 *         method for plus button.
	 */
	private class PlusHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {

			int personCount = Integer.parseInt(bookBoatShopListView.getPersonCount());
			personCount++;
			bookBoatShopListView.enableMinusButton();
			bookBoatShopListView.setPersonCountText(String.valueOf(personCount));

		}

	}

	/**
	 *         This class implements the EventHandler interface providing the
	 *         handler method for search button.
	 */
	private class SearchHandler implements EventHandler<ActionEvent> {

		private boolean fieldsAreFilled() {
			return !bookBoatShopListView.getCityField().isEmpty() && bookBoatShopListView.getCheckInDate() != null
					&& bookBoatShopListView.getCheckOutDate() != null
					&& Integer.parseInt(bookBoatShopListView.getPersonCount()) != 0;
		}

		private boolean checkInDateIsBeforeCheckOutDate() {
			return bookBoatShopListView.getCheckInDate().isBefore(bookBoatShopListView.getCheckOutDate())
					|| bookBoatShopListView.getCheckInDate().equals(bookBoatShopListView.getCheckOutDate());

		}

		private void checkEmptyFields() {
			if (bookBoatShopListView.getCityField().isEmpty())
				bookBoatShopListView.setVisibleErrCityField(true);

			if (bookBoatShopListView.getCheckInDate() == null)
				bookBoatShopListView.setVisibleErrCheckInField(true);

			if (bookBoatShopListView.getCheckOutDate() == null)
				bookBoatShopListView.setVisibleErrCheckOutField(true);

			if (Integer.parseInt(bookBoatShopListView.getPersonCount()) == 0)
				bookBoatShopListView.setVisibleErrPersonCount(true);
		}

		@Override
		public void handle(ActionEvent event) {

			/* Set invisible all errors. */
			bookBoatShopListView.setVisibleErrCityField(false);
			bookBoatShopListView.setVisibleErrCheckInField(false);
			bookBoatShopListView.setVisibleErrCheckOutField(false);
			bookBoatShopListView.setVisibleErrPersonCount(false);

			if (this.fieldsAreFilled()) {

				/* The user filled all the fields. */
				if (this.checkInDateIsBeforeCheckOutDate()) {

					/* The check-in date is before or equal to the check-out date. */

					/* Fill the bean fields. */
					fields.setCity(bookBoatShopListView.getCityField());
					fields.setCheckIn(bookBoatShopListView.getCheckInDate());
					fields.setCheckOut(bookBoatShopListView.getCheckOutDate());
					fields.setPersonCount(Integer.parseInt(bookBoatShopListView.getPersonCount()));

					List<BoatShopBean> rentalShops = model.retrieveBoatShopByCity(fields.getCity());

					if (rentalShops.isEmpty())

						/* The input provided by the user doesn't provide result */
						bookBoatShopListView.resultNotFound();

					else

						bookBoatShopListView.populateView(rentalShops, new MoreInformationHandler());

				} else {

					/* The Check-Out date is before the Check-In date. */
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Information");
					alert.setHeaderText(null);
					alert.setContentText("Check-Out cannot be before Check-In date.");

					alert.showAndWait();

				}

			} else {

				/* The user doesn't fill all the fields. */
				this.checkEmptyFields();

			}

		}

	}

	/**
	 *         This class implements the EventHandler interface providing the handle
	 *         for the MoreinformationButton.
	 */
	public class MoreInformationHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {

			/* Fill the bean with the data provided by the user. */
			fields.setCity(bookBoatShopListView.getCityField());
			fields.setCheckIn(bookBoatShopListView.getCheckInDate());
			fields.setCheckOut(bookBoatShopListView.getCheckOutDate());
			fields.setPersonCount(Integer.parseInt(bookBoatShopListView.getPersonCount()));

			int id = Integer.parseInt(((Control) event.getSource()).getId()); // The id of the boat shop selected.

			/* Change the view to HotelView and initialize the new controller. */
			Main.getInstance().changeToBoatShopView();
			new BoatShopViewController(Main.getInstance().getBoatShopView(), model.getRentalShop(id), fields);

		}

	}

}
