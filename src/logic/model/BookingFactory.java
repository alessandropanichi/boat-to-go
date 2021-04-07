package logic.model;

import java.time.LocalDate;
import java.util.List;

/**
 * 
 *         Singleton class. Factory class. It has the responsibility of
 *         creating classes that specialize the Booking class.
 */
public class BookingFactory {

	/**
	 * Reference to an instance of this class.
	 */
	private static BookingFactory instance = null;

	/**
	 * Create a new ActiveBooking.
	 * 
	 * @param boatShop
	 * @param user
	 * @param checkIn
	 * @param checkOut
	 * @param people
	 * @return new ActiveBooking.
	 */
	public Booking createBooking(String boatShop, String user, LocalDate checkIn, LocalDate checkOut,
			List<Person> people) {

		return new Booking(boatShop, user, checkIn, checkOut, people);

	}

	/**
	 * Get instance method.
	 * 
	 * @return The instance of this class.
	 */
	public static synchronized BookingFactory getInstance() {

		if (BookingFactory.instance == null)

			BookingFactory.instance = new BookingFactory();

		return BookingFactory.instance;

	}

}
