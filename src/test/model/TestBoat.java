package test.model;

import logic.model.Booking;
import logic.model.Person;
import logic.model.Boat;
import org.junit.Test;
import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *         This is a test class for the logic class Boat. It tests the method
 *         isAvailable() in different ways.
 */
public class TestBoat {

	/**
	 * Setup method for running the test.
	 * 
	 * @return boat with mock data.
	 */
	public Boat setup() {

		Boat boat = new Boat("prova", 2, 20, 1);

		List<Person> people = new ArrayList<>();

		boat.addActiveBooking(new Booking(null, null, LocalDate.of(2020, 1, 5), LocalDate.of(2020, 1, 5), people));

		boat.addActiveBooking(new Booking(null, null, LocalDate.of(2020, 1, 6), LocalDate.of(2020, 1, 10), people));

		boat.addActiveBooking(new Booking(null, null, LocalDate.of(2050, 1, 1), LocalDate.of(2050, 1, 7), people));

		boat.addActiveBooking(new Booking(null, null, LocalDate.of(2021, 1, 1), LocalDate.of(2022, 1, 1), people));

		return boat;

	}

	@Test
	public void testIsAvailableNotAvailable() {

		Boat boat = this.setup();

		boolean available = boat.isAvailable(LocalDate.of(2020, 1, 4), LocalDate.of(2020, 1, 6));

		assertEquals(false, available);

	}

	@Test
	public void testIsAvailableAvailable() {

		Boat boat = this.setup();

		boolean available = boat.isAvailable(LocalDate.of(2020, 1, 11), LocalDate.of(2020, 12, 31));

		assertEquals(true, available);

	}

	@Test
	public void testIsAvailableLongIntervalDate() {

		Boat boat = this.setup();

		boolean available = boat.isAvailable(LocalDate.of(2021, 2, 1), LocalDate.of(2021, 10, 1));

		assertEquals(false, available);

	}

	@Test
	public void testIsAvailableSameDateNotAvailable() {

		Boat boat = this.setup();

		boolean available = boat.isAvailable(LocalDate.of(2020, 1, 5), LocalDate.of(2020, 1, 5));

		assertEquals(false, available);

	}

	@Test
	public void testIsAvailableSameDateAvailable() {

		Boat boat = this.setup();

		boolean available = boat.isAvailable(LocalDate.of(2020, 1, 11), LocalDate.of(2020, 1, 11));

		assertEquals(true, available);

	}

}
