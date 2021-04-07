package logic.bean;

public class BoatBean {

	private String description;

	private int seats;

	private int size;

	private int toilets;

	private int id;

	private int availability;

	private int boatChoise;

	public BoatBean() {
	}

	public BoatBean(String description, int seats, int size, int toilets, int id) {

		this.description = description;
		this.seats = seats;
		this.size = size;
		this.toilets = toilets;
		this.id = id;

	}

	public int getBoatChoise() {
		return boatChoise;
	}

	public void setBoatChoise(String boatChoise) {
		this.boatChoise = Integer.parseInt(boatChoise);
	}

	public int getSeats() {
		return seats;
	}

	public void setSeats(String seats) {
		this.seats = Integer.parseInt(seats);
	}

	public int getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = Integer.parseInt(availability);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getToilets() {
		return toilets;
	}

	public void setToilets(int toilets) {
		this.toilets = toilets;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
