package logic.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import logic.bean.BookingBean;
import logic.bean.BoatBean;
import logic.model.bookingState.StateEnum;
import logic.model.dao.BookingDAO;
import logic.model.dao.BookingDAOImpl;
import logic.model.dao.PersonDAO;
import logic.model.dao.PersonDAOImpl;

/**
 *         This class represent a boat. It is a part of a rental shop. It
 *         exposes methods for checking the availability of itself for the given
 *         date.
 */
public class Boat {
	/**
	 * User for identifying the boat in the boat shop.
	 */
	private int id;

	/**
	 * The description of the boat.
	 */
	private String description;

	/**
	 * The number of seats that the boat contain.
	 */
	private int seats;

	/**
	 * The size of the boat, measured in square meters.
	 */
	private int size;

	/**
	 * Number of the toilets available.
	 */
	private int toilets;

	/**
	 * A reference to active bookings. Never pass it out of this class.
	 */
	private List<Booking> bookings;

	/**
	 * The constructor of the class.
	 * 
	 * @param description the description.
	 * @param seats       number of seats.
	 * @param size        size, measured in square meters.
	 * @param toilets     number of toilets.
	 */
	public Boat(String description, int seats, int size, int toilets) {

		this.description = description;

		this.seats = seats;

		this.size = size;

		this.toilets = toilets;

		this.bookings = new ArrayList<>();

	}

	/**
	 * The constructor that take the bean as parameter.
	 * 
	 * @param boatBean the bean that contain data of the boat.
	 */
	public Boat(BoatBean boatBean) {
		this(boatBean.getDescription(), boatBean.getSeats(), boatBean.getSize(), boatBean.getToilets());
		this.id = boatBean.getId();

		BookingDAO dao = new BookingDAOImpl();
		List<BookingBean> bookingBeans = dao.getAllBookingOfABoat(boatBean.getId());

		for (BookingBean bookingBean : bookingBeans) {
			this.bookings.add(new Booking(bookingBean));
		}
	}

	/**
	 * Check if the boat is available for the given date.
	 * 
	 * @param checkIn  the Check-In date.
	 * @param checkOut the Check-Out date.
	 * @return true if the boat is available, false otherwise.
	 */
	public boolean isAvailable(LocalDate checkIn, LocalDate checkOut) {

		for (Booking booking : this.getActiveBooking()) {

			/* For each active booking. */
			if (checkIn.isBefore(booking.getCheckOut()) && checkOut.isAfter(booking.getCheckIn()))

				/* The given date conflicts with another date of a booking. */
				return false;

			if (checkIn.equals(booking.getCheckIn()) && checkOut.equals(booking.getCheckOut()))

				/* The given date conflict exactly with another date. */
				return false;
		}

		return true;

	}

	/**
	 * Get all booking that are in submitted state or accepted state.
	 * 
	 * @return all active booking.
	 */
	public List<Booking> getActiveBooking() {

		List<Booking> activeBooking = new ArrayList<>();
		StateEnum state;

		for (Booking booking : this.bookings) {
			state = booking.getEnumValueOfState();

			switch (state) {
			case SUBMITTED:
				activeBooking.add(booking);
				break;
			case ACCEPTED:
				activeBooking.add(booking);
				break;
			default:
				break;
			}

		}

		return activeBooking;

	}

	/**
	 * Find the booking for the given id, if exist.
	 * 
	 * @param id the id of the booking to find.
	 * @return the booking that match the id, if exist, otherwise null.
	 */
	public Booking getBooking(int id) {

		for (Booking booking : this.bookings)
			if (booking.getId() == id)
				return booking;

		return null;

	}

	/**
	 * Add a new booking to the boat, and add a row in the database.
	 * 
	 * @param activeBooking the new booking.
	 */
	public void addActiveBooking(Booking newBooking) {

		this.bookings.add(newBooking);
		BookingBean bookingPOJO = new BookingBean();
		bookingPOJO.setCheckIn(newBooking.getCheckIn());
		bookingPOJO.setCheckOut(newBooking.getCheckOut());
		bookingPOJO.setState(newBooking.getEnumValueOfState());
		bookingPOJO.setUser(newBooking.getUser());

		BookingDAO dao = new BookingDAOImpl();
		int idBooking = dao.createBooking(bookingPOJO, this.id);

		PersonDAO personDAO = new PersonDAOImpl();
		for (Person person : newBooking.getPeople()) {
			personDAO.createPerson(person, idBooking);
		}

	}

	/**
	 * Get the description.
	 * 
	 * @return the description attribute.
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Get the number of seats contained in that boat.
	 * 
	 * @return the seats attribute.
	 */
	public int getSeats() {
		return this.seats;
	}

	/**
	 * Get the size of the boat, measured in square meters.
	 * 
	 * @return the size attribute.
	 */
	public int getSize() {
		return this.size;
	}

	/**
	 * Get the number of toilets.
	 * 
	 * @return the toilets attribute.
	 */
	public int getToilets() {
		return this.toilets;
	}

	/**
	 *         the follow method set the attribute for a boat
	 */
	public void setSeats(int i) {
		this.seats = i;

	}

	public void setSize(int i) {
		this.size = i;

	}

	public void setToilets(int i) {
		this.toilets = i;

	}

	public void setDescription(String s) {
		this.description = s;

	}

	/**
	 * Retrieve all bookings made by the user for this boat.
	 * 
	 * @param username
	 * @return list of booking.
	 */
	public List<Booking> getAllBookingOfThisUser(String username) {
		List<Booking> userBookings = new ArrayList<>();

		for (Booking booking : this.bookings) {

			if (booking.getUser().equals(username))
				userBookings.add(booking);

		}

		return userBookings;
	}

}
