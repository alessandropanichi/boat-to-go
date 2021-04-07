package logic.model;

import java.util.ArrayList;
import java.util.List;

import logic.bean.BookingBean;
import logic.bean.CityDateBean;
import logic.bean.BoatShopBean;
import logic.bean.LoginBean;
import logic.bean.BoatBean;
import logic.model.dao.BookingDAO;
import logic.model.dao.BookingDAOImpl;
import logic.model.dao.BoatShopDAO;
import logic.model.dao.BoatShopDAOImpl;
import logic.model.dao.PersonDAO;
import logic.model.dao.PersonDAOImpl;

/**
 *         Singleton class. This class represent the controller of the use case
 *         "Book Boat".
 */
public class BookBoatShopController {

	/**
	 * Reference to a list of all rental shop.
	 */
	private List<RentalShop> rentalShops;

	/**
	 * Reference to an instance of this class.
	 */
	private static BookBoatShopController instance = null;

	/**
	 * Constructor of the class. Creates all the rental shops contained in
	 * database.
	 */
	private BookBoatShopController() {

		this.rentalShops = new ArrayList<>();

	}

	/**
	 * Retrieve all boat shops placed in a city.
	 * 
	 * @param city the name of the city.
	 * @return the list of bean that contain data.
	 */
	public List<BoatShopBean> retrieveBoatShopByCity(String city) {

		BoatShopDAO dao = new BoatShopDAOImpl();
		return dao.getAllBoatShopByCity(city);

	}

	/**
	 * 
	 * @param id the id of the rental shop we are searching.
	 * @return the rental shop that match with the id.
	 */
	public RentalShop getRentalShop(int id) {

		BoatShopDAO dao = new BoatShopDAOImpl();
		BoatShopBean boatShopBean = dao.getBoatShop(id);
		RentalShop boatShop = new BoatShop(boatShopBean);

		this.rentalShops.add(boatShop);

		return boatShop;

	}

	/**
	 * Retrieve the booking by the id and change its state to delete.
	 * 
	 * @param id the booking to delete.
	 */
	public void deleteBooking(int id) {

		BookingBean bookingBean = new BookingDAOImpl().getBooking(id);

		Booking booking = new Booking(bookingBean);
		booking.delete();

	}

	public void resubmitBooking(int id) {
		BookingBean bookingBean = new BookingDAOImpl().getBooking(id);

		Booking booking = new Booking(bookingBean);
		booking.resubmit();
	}

	/**
	 * Retrieve all rental shops available for the fields passed.
	 * 
	 * @param fields bean that contains input of the user.
	 * @return list of rental shops that satisfy the condition.
	 */
	public List<RentalShop> retrieveRentalShop(CityDateBean fields) {

		List<RentalShop> searchResult = new ArrayList<>();

		for (RentalShop rentalShop : this.rentalShops) {

			if (rentalShop.getCity().equals(fields.getCity()) && rentalShop.isAvailable(fields))

				/* The rental shop is available and and is located in the specified city. */
				searchResult.add(rentalShop);

		}

		return searchResult;

	}

	/**
	 * Create a new booking and add it to the specified rental shop.
	 * 
	 * @param loginBean
	 * @param fields
	 * @param people
	 * @param boatBeans
	 * @param rentShop
	 */
	public void createBooking(CityDateBean fields, List<Person> people, List<BoatBean> boatBeans,
			RentalShop rentShop) {

		Booking booking = BookingFactory.getInstance().createBooking(rentShop.getName(),
				LoginController.getInstance().getUsername(), fields.getCheckIn(), fields.getCheckOut(), people);

		rentShop.addActiveBooking(booking, boatBeans);

	}

	/**
	 * Retrieve all booking made by an user in the system.
	 * 
	 * @param loginBean input of the user.
	 * @return
	 */
	public List<BookingBean> retrieveBookingOfAnUser(LoginBean loginBean) {

		List<BookingBean> bookings = new ArrayList<>();

		BookingDAO dao = new BookingDAOImpl();
		bookings.addAll(dao.getAllBookingOfAUser(loginBean.getUsername()));

		PersonDAO daoPerson = new PersonDAOImpl();
		for (BookingBean bean : bookings) {
			bean.setPeople(daoPerson.getAllPeopleOfABooking(bean.getBookingId()));
		}

		return bookings;

	}

	/**
	 * Get instance method.
	 * 
	 * @return the only instance of this class.
	 */
	public static synchronized BookBoatShopController getInstance() {

		if (BookBoatShopController.instance == null)

			BookBoatShopController.instance = new BookBoatShopController();

		return BookBoatShopController.instance;

	}

}
