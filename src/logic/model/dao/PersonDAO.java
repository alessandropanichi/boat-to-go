package logic.model.dao;

import java.util.List;

import logic.model.Person;

public interface PersonDAO {
	/**
	 * Find all people of a booking.
	 * 
	 * @param bookingId the booking id.
	 * @return list of people that match with booking id.
	 */
	public List<Person> getAllPeopleOfABooking(int bookingId);

	/**
	 * Create a new person on the database.
	 * 
	 * @param person    the person.
	 * @param idBooking the id of the booking that contain the person.
	 * @return the id of the person.
	 */
	public int createPerson(Person person, int idBooking);

}
