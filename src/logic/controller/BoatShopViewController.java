package logic.controller;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Control;
import logic.Main;
import logic.bean.CityDateBean;
import logic.bean.BoatBean;
import logic.model.RentalShop;
import logic.model.BookBoatShopController;
import logic.model.LoginController;
import logic.view.BoatShopView;

/**
 * MVC controller for the view BoatShopView.
 */
public class BoatShopViewController extends MainViewController {
	
	/**
	 * Reference to the view.
	 */
	private BoatShopView boatShopView;
	
	/**
	 * Reference to the model.
	 */
	private RentalShop boatShopModel;
	
	/**
	 * Reference to the bean that contain data input of the user.
	 */
	private CityDateBean fields;
	
	/**
	 * Constructor of the class.
	 * It initialize the state of the controller. Also set the data to the view.
	 * 
	 * @param view		the view.
	 * @param model		the model.
	 * @param fields	bean that contains data input of the user.
	 */
	public BoatShopViewController(BoatShopView view, RentalShop model, CityDateBean fields) {
		
		super(view);
		
		/* Set the new view and the new model. */
		this.boatShopView = (BoatShopView) super.view;
		this.boatShopModel = model;
		this.fields = fields;
		
		/* Set the data to the view. */
		this.boatShopView.setName(this.boatShopModel.getName());
		this.boatShopView.setAddress(this.boatShopModel.getAddress());
		this.boatShopView.setInformation(this.boatShopModel.getDescription());
		
		/* Set the view to represent how much boats are available. */
		this.setBoatAvailability();
		
		/* Add handlers to button. */
		this.boatShopView.addBackHandler(new BackHandler());
		this.boatShopView.addBookHandler(new BookHandler());
		
	}
	
	/**
	 * Set the view to represent how much boats are available
	 */
	public void setBoatAvailability() {
		
		List<BoatBean> boatsAvailability = boatShopModel.getAvailableBoats(fields);
		
		this.boatShopView.createBoatSelector(boatsAvailability, new PlusHandler(), new MinusHandler());
		
	}
	
	/**
	 * This class implements the EventHandler interface for the plus button.
	 */
	private class PlusHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			
			int index = Integer.parseInt( ((Control)event.getSource()).getId() ); 	/* The id of the button clicked. */
			
			BoatShopView.BoatSelector boatSelector = boatShopView.getBoatSelector(index);		/* The component of the view that contains the button clicked. */
			
			int numberOfBoats = Integer.parseInt( boatSelector.getBoatChoise() );	/* Number of boats entered by the user. */
			
			if( numberOfBoats < Integer.parseInt(boatSelector.getBoatAvailability()) ) {
			
				/* Number of boats entered are less than the availability. */
				
				numberOfBoats++;		/* Increase the value. */
				
				boatSelector.setBoatChoise( String.valueOf( numberOfBoats ) );	/* Set the new value. */
				
				boatSelector.enableMinusButton();		/* After the press of the plus button the minus button can be enabled. */
				
				if( numberOfBoats == Integer.parseInt(boatSelector.getBoatAvailability()) )
					
					/* The number of boats selected by the user reached the total availability. */
					boatSelector.disablePlusButton();
				
			}
			
			int totalSeats = 0;		/* Counter for the seats. */
			
			for( BoatShopView.BoatSelector boatSel : boatShopView.getAllBoatSelectors() ) {
				
				numberOfBoats = Integer.parseInt( boatSel.getBoatChoise() );	/* Number of boats choice for this seats. */
				
				int numberOfSeats = Integer.parseInt( boatSel.getNumberOfSeats() );	/* Number of seats for this type of boat. */
				
				totalSeats = totalSeats + numberOfBoats*numberOfSeats;			/* Update total seats. */
				
			}
			
			if( totalSeats >= fields.getPersonCount() ) {
				
				/* Total seats for the boats selected reached the person count, so all the plus buttons can be disabled. */
				for (BoatShopView.BoatSelector boatSel : boatShopView.getAllBoatSelectors() ) {
					
					boatSel.disablePlusButton();
					
				}
				
			}			
			
		}		
		
	}
	
	/**
	 * This class provides the implementation of EventHandler interface for the minus button.
	 */
	private class MinusHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			
			int index = Integer.parseInt( ((Control)event.getSource()).getId() );  	/* The id of the button clicked. */
			
			BoatShopView.BoatSelector boatSelector = boatShopView.getBoatSelector(index);		/* The corresponding part of the view for the button clicked. */
			
			int numberOfBoats = Integer.parseInt( boatSelector.getBoatChoise() );	/* Number of boats selected by the user. */
			
			numberOfBoats--;	/* Decrease the counter. */
			
			if( numberOfBoats == 0 )
				
				/* The counter reached 0, so the minus button can be disabled */
				boatSelector.disableMinusButton();
			
			boatSelector.setBoatChoise( String.valueOf( numberOfBoats ) );		/* Update with new value. */
			
			for (BoatShopView.BoatSelector boatSel : boatShopView.getAllBoatSelectors() ) {
				
				/* After a decrease all the plus button can be enabled. */
				boatSel.enablePlusButton();
				
			}
			
		}
		
	}
	
	/**
	 * This class provides the implementation of EventHandler interface for the book button.
	 */
	private class BookHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			
			if(LoginController.getInstance().isLogged()) {
			
				List<BoatBean> boatBeans = new ArrayList<>();	/* List of beans. */
				
				boatShopView.setErrVisible(false);
				
				int totalSeats = 0;
				
				for(BoatShopView.BoatSelector boatSelector : boatShopView.getAllBoatSelectors()) {
					
					if( Integer.parseInt(boatSelector.getBoatChoise()) > 0) {
					
						/* The user has selected this type of boat.  */
						BoatBean boatBean = new BoatBean();
						
						boatBean.setSeats(boatSelector.getNumberOfSeats());
						
						boatBean.setBoatChoise(boatSelector.getBoatChoise());
						
						boatBeans.add(boatBean);
						
						totalSeats += Integer.parseInt(boatSelector.getNumberOfSeats()) * Integer.parseInt(boatSelector.getBoatChoise());
						
					}
					
				}
				
				if( totalSeats >= fields.getPersonCount() ) {					
						
					/* Change view and start new controller. */
					Main.getInstance().changeToBookingView();
					new BookingViewController(Main.getInstance().getBookingView(), boatShopModel, fields, boatBeans);					
					
				} else {
					
					boatShopView.setErrVisible(true);
					
				}
			
			} else {
				
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information");
				alert.setHeaderText(null);
				alert.setContentText("You have to login before booking!");
				
				alert.showAndWait();
				
			}
			
		}
		
	}
	
	/**
	 * This class provides the implementation of EventHandler interface for the back button.
	 */
	private class BackHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {			
				
			/* Change the view and start new controller. */
			Main.getInstance().changeToBookBoatShopListView();
			new BookBoatShopListViewController(Main.getInstance().getBookBoatShopListView(),	BookBoatShopController.getInstance(), fields);			
			
		}
		
	}

}



