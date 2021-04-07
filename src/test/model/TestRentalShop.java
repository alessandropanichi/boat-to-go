package test.model;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import logic.bean.CityDateBean;
import logic.bean.BoatBean;
import logic.model.Booking;
import logic.model.BoatShop;
import logic.model.Person;
import logic.model.RentalShop;
import logic.model.Boat;

/**
 *         Test class for logic class RentalShop.
 */
public class TestRentalShop {

	/**
	 * Setup method used for running the test.
	 * 
	 * @return RentalShop with mock data.
	 */
	public RentalShop setup() {

		RentalShop rentalShop = new BoatShop("nome", "indirizzo", "citta", "Proprietario");

		List<Person> people = new ArrayList<>();

		Boat boat1 = new Boat("Barca 1", 2, 20, 1);
		boat1.addActiveBooking(new Booking(null, null, LocalDate.of(2020, 1, 5), LocalDate.of(2020, 1, 5), people));
		boat1.addActiveBooking(new Booking(null, null, LocalDate.of(2020, 1, 6), LocalDate.of(2020, 2, 10), people));
		rentalShop.addNewBoat(boat1);

		Boat boat2 = new Boat("Barca 2", 3, 25, 1);
		boat2.addActiveBooking(new Booking(null, null, LocalDate.of(2020, 1, 5), LocalDate.of(2020, 1, 5), people));
		boat2.addActiveBooking(new Booking(null, null, LocalDate.of(2020, 2, 5), LocalDate.of(2020, 2, 15), people));
		rentalShop.addNewBoat(boat2);

		Boat boat3 = new Boat("Barca 3", 4, 30, 1);
		boat3.addActiveBooking(new Booking(null, null, LocalDate.of(2020, 1, 5), LocalDate.of(2020, 1, 5), people));
		boat3.addActiveBooking(new Booking(null, null, LocalDate.of(2020, 2, 5), LocalDate.of(2020, 2, 15), people));
		rentalShop.addNewBoat(boat3);

		Boat boat4 = new Boat("Barca 4", 5, 30, 2);
		boat4.addActiveBooking(new Booking(null, null, LocalDate.of(2020, 1, 5), LocalDate.of(2020, 1, 5), people));
		boat4.addActiveBooking(new Booking(null, null, LocalDate.of(2020, 5, 5), LocalDate.of(2020, 6, 15), people));
		rentalShop.addNewBoat(boat4);

		Boat boat5 = new Boat("Barca 5", 2, 30, 1);
		Boat boat6 = new Boat("Barca 6", 2, 30, 1);
		Boat boat7 = new Boat("Barca 7", 2, 30, 1);
		Boat boat8 = new Boat("Barca 8", 3, 30, 1);
		rentalShop.addNewBoat(boat5);
		rentalShop.addNewBoat(boat6);
		rentalShop.addNewBoat(boat7);
		rentalShop.addNewBoat(boat8);

		return rentalShop;

	}

	@Test
	public void testIsAvailableAvailable() {

		CityDateBean fields = new CityDateBean();

		fields.setCheckIn(LocalDate.of(2020, 1, 11));
		fields.setCheckOut(LocalDate.of(2020, 1, 15));
		fields.setPersonCount(1);

		RentalShop rentalShop = this.setup();

		boolean available = rentalShop.isAvailable(fields);

		assertEquals(true, available);

	}

	@Test
	public void testIsAvailableBigNumberNotAvailable() {

		CityDateBean fields = new CityDateBean();

		fields.setCheckIn(LocalDate.of(2020, 1, 11));
		fields.setCheckOut(LocalDate.of(2020, 1, 15));
		fields.setPersonCount(1000);

		RentalShop rentalShop = this.setup();

		boolean available = rentalShop.isAvailable(fields);

		assertEquals(false, available);

	}

	@Test
	public void testIsAvailableLimitNumberAvailable() {

		CityDateBean fields = new CityDateBean();

		fields.setCheckIn(LocalDate.of(2020, 1, 1));
		fields.setCheckOut(LocalDate.of(2020, 1, 1));
		fields.setPersonCount(14); /* The total availability of the boat shop is 14. */

		RentalShop rentalShop = this.setup();

		boolean available = rentalShop.isAvailable(fields);

		assertEquals(true, available);

	}

	@Test
	public void testGetNumberOfBoatsBySeatsTwoSeatsWithoutActiveBooking() {

		CityDateBean fields = new CityDateBean();

		fields.setCheckIn(LocalDate.of(2020, 1, 1));
		fields.setCheckOut(LocalDate.of(2020, 1, 2));

		RentalShop rentalShop = this.setup();

		BoatBean actual = rentalShop.getNumberOfBoatBySeats(2, fields);

		assertEquals(2, actual.getSeats());
		assertEquals(4, actual.getAvailability());

	}

	@Test
	public void testGetNumberOfBoatsBySeatsTwoSeatsWithActiveBooking() {

		CityDateBean fields = new CityDateBean();

		fields.setCheckIn(LocalDate.of(2020, 1, 5));
		fields.setCheckOut(LocalDate.of(2020, 1, 10));

		RentalShop rentalShop = this.setup();

		BoatBean actual = rentalShop.getNumberOfBoatBySeats(2, fields);

		assertEquals(2, actual.getSeats());
		assertEquals(3, actual.getAvailability());

	}

	@Test
	public void testGetBoatsTwoSeatsFiveSeats() {

		RentalShop boatShop = this.setup();

		Boat boatExpected1 = new Boat("Barca 10", 5, 30, 2);
		Boat boatExpected2 = new Boat("Barca 11", 5, 30, 2);

		boatShop.getBoats().add(boatExpected1);
		boatShop.getBoats().add(boatExpected2);

		List<Boat> actual = boatShop.getBoats(5, 2, LocalDate.of(2020, 1, 5), LocalDate.of(2020, 1, 5));

		assertEquals(2, actual.size());
		assertEquals(true, actual.contains(boatExpected1));
		assertEquals(true, actual.contains(boatExpected2));

	}

	@Test
	public void testGetBoatsOneBoatsFiveSeats() {

		RentalShop boatShop = this.setup();

		Boat boatExpected1 = new Boat("Barca 10", 5, 30, 2);

		boatShop.getBoats().add(boatExpected1);
		boatShop.getBoats().add(new Boat("Barca 11", 5, 30, 2));

		List<Boat> actual = boatShop.getBoats(5, 1, LocalDate.of(2020, 1, 5), LocalDate.of(2020, 1, 5));

		assertEquals(1, actual.size());
		assertEquals(true, actual.contains(boatExpected1));

	}

}
