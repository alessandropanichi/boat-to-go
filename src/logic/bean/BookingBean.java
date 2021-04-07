package logic.bean;

import java.time.LocalDate;
import java.util.List;

import logic.model.Person;
import logic.model.bookingState.StateEnum;

public class BookingBean {

	private String boatShop;

	private LocalDate checkIn;

	private LocalDate checkOut;

	private List<Person> people;

	private String user;

	private StateEnum state;

	private int bookingId;

	public BookingBean() {
	}

	public BookingBean(String boatShop, LocalDate checkIn, LocalDate checkOut, StateEnum state, String user,
			int bookingId) {

		this.boatShop = boatShop;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.state = state;
		this.user = user;
		this.bookingId = bookingId;

	}

	public BookingBean(String boatShop, LocalDate checkIn, LocalDate checkOut, List<Person> people, StateEnum state,
			int bookingId, String user) {
		this(boatShop, checkIn, checkOut, state, user, bookingId);

		this.people = people;

	}

	public String getBoatShop() {
		return boatShop;
	}

	public void setBoatShop(String boatShop) {
		this.boatShop = boatShop;
	}

	public LocalDate getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(LocalDate checkIn) {
		this.checkIn = checkIn;
	}

	public LocalDate getCheckOut() {
		return checkOut;
	}

	public void setCheckOut(LocalDate checkOut) {
		this.checkOut = checkOut;
	}

	public List<Person> getPeople() {
		return people;
	}

	public void setPeople(List<Person> people) {
		this.people = people;
	}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public StateEnum getState() {
		return state;
	}

	public void setState(StateEnum state) {
		this.state = state;
	}

}
