package logic.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import logic.bean.CityDateBean;
import logic.bean.BoatBean;


/**
 * This class represent a rental shop. It is abstract, then if you want to use its 
 * methods you have to instantiate its concrete subclasses.
 * This class exposes methods to manage the boats the shop offer.
 */
public abstract class RentalShop {
	
	/**
	 * A reference to boats offered by the shop.
	 * Never pass this reference out of this class.
	 */
	protected List<Boat> boats;
	
	/**
	 * The name of the rental shop.
	 */
	protected String name;
	
	/**
	 * The address of the rental shop.	
	 */
	protected String address;
	
	/**
	 * The city where the rental shop is located.
	 */
	protected String city;
	
	/**
	 * The description of the rental shop.
	 */
	protected String description;

	protected String owner;
	
	/**
	 * Constructor of the class. It initialize the state of the object and
	 * creates the boats the shop offer, retrieving them from database.
	 * 
	 * @param name
	 * @param address
	 * @param city
	 */
	public RentalShop(String name, String address, String city, String owner) {
		
		this.boats = new ArrayList<>();
		
		this.name = name;
		this.address = address;
		this.city = city;
		this.owner = owner;
		
	}
	
	/**
	 * Another constructor, it take as a parameter the list of the boats.
	 * @param name
	 * @param address
	 * @param city
	 * @param boats
	 */
	public RentalShop(String name, String address, String city, List<Boat> boats, String owner) {
		
		this(name, address, city, owner);
		
		this.boats = new ArrayList<>(boats);
		
	}
	
	/**
	 * Another constructor, it also set the description.
	 * 
	 * @param name
	 * @param address
	 * @param city
	 * @param description
	 */
	public RentalShop(String name, String address, String city, String description, String owner) {
		
		this.name = name;
		
		this.address = address;
		
		this.city = city;
		
		this.description = description;
		
		this.owner = owner;
		
		this.boats = new ArrayList<>();
		
	}
	
	/**
	 * Add a new boat to the rental shop.
	 * 
	 * @param description	description.
	 * @param seats			number of seats.
	 * @param size			size, measured in square meters.
	 * @param toilets		number of toilets.
	 */
	public void addNewBoat(String description, int seats, int size, int toilets) {
		
		this.boats.add(new Boat(description, seats, size, toilets));
		
	}
	
	/**
	 * Method used for testing.
	 * 
	 * @param newBoat	the new boat.
	 */
	public void addNewBoat(Boat newBoat) {
		
		this.boats.add(newBoat);
		
	}
	
	/**
	 * Check if there is a boat available for the data contained in fields.
	 * 
	 * @param fields	bean that contain Check-In date, Check-Out date and number of people that want to book.
	 * @return			true if there is boats available for containing all people, false otherwise.
	 */
	public boolean isAvailable(CityDateBean fields) {
		
		int availability = 0;	/* Counter to keep track of how much seats are available. */
		
		for( Boat boat : this.boats ) {
			
			if( boat.isAvailable(fields.getCheckIn(), fields.getCheckOut()) )
				
				/* The boat is available for the given date. */
				availability += boat.getSeats();
			
		}
		
		return availability >= fields.getPersonCount();
		
	}
	
	/**
	 * Check how many boats of a specified number of seats are available.
	 * 
	 * @param seats		the number of seats
	 * @param fields	bean that contain fields, such as Check-In date and Check-Out date
	 * @return			the bean BoatBean, that represent how many boats are available for the specified number of seats.
	 */
	public BoatBean getNumberOfBoatBySeats(int seats, CityDateBean fields) {
		
		BoatBean boatBean = new BoatBean();
		
		int availability = 0; 	/* Counter to keep track of availability. */
		
		for( Boat boat : this.boats ) {
			
			if( boat.isAvailable(fields.getCheckIn(), fields.getCheckOut()) && boat.getSeats() == seats )
				
				/* The boat is available and the number of seats it contains are equal to the one specified in formal parameter. */
				availability++;
			
		}
		
		boatBean.setSeats(String.valueOf(seats));
		
		boatBean.setAvailability(String.valueOf(availability));
		
		return boatBean;
		
	}
	/**
	 * Get the boats that are available for the given input.
	 * 
	 * @param fields input of the user.
	 * @return	the list of boats available.
	 */
	public List<BoatBean> getAvailableBoats(CityDateBean fields) {
		List<BoatBean> boatsAvailable = new ArrayList<>();
		
		if(fields.getPersonCount() == 1) {
			boatsAvailable.add(this.getNumberOfBoatBySeats(2, fields));
		} else {
			for(int i = 1; i <= fields.getPersonCount(); i++) {
				BoatBean boatBean = this.getNumberOfBoatBySeats(i, fields);
				if(boatBean.getAvailability() != 0)
					boatsAvailable.add(boatBean);
			}
		}
		
		return boatsAvailable;
	}
	
	/**
	 * It finds an amount of boats specified by "number" that contain an amount of "seats" and that 
	 * are available for the given date.
	 * 
	 * @param seats			the number of seats that the boats must contain.
	 * @param number		the number of the boats necessary.
	 * @param checkIn		the Check-In date.
	 * @param checkOut		the Check-Out date.
	 * @return				list of boats that satisfy the condition.
	 */
	public List<Boat> getBoats(int seats, int number, LocalDate checkIn, LocalDate checkOut) {
		
		List<Boat> availableBoats = new ArrayList<>();
		
		int i = 0;	/* Counter for sliding the list. */
		
		while(number > 0) {
			
			/* While the number of boats added are not satisfied. */
			Boat boat = this.boats.get(i);
			
			if( boat.isAvailable(checkIn, checkOut) && boat.getSeats() == seats ) {
				
				/* The boat is available for the given date and contain the specified amount of seats. */
				availableBoats.add(boat);
				
				number--;
				
			}
			
			i++;
			
		}
		
		return availableBoats;
		
	}
	
	/**
	 * Add a new booking to the boats specified in boatBeans.
	 * 
	 * @param newBooking		the new booking.
	 * @param boatBeans			the list of BoatBean. Each BoatBean must contain the number of seats required
	 * 							and the number of boat required for this specified seats.
	 */
	public void addActiveBooking(Booking newBooking, List<BoatBean> boatBeans) {
		
		LocalDate checkIn = newBooking.getCheckIn();
		
		LocalDate checkOut = newBooking.getCheckOut();
		
		for( BoatBean boatBean : boatBeans ) {
			
			for( Boat boat : this.getBoats(boatBean.getSeats(), boatBean.getBoatChoise(), checkIn, checkOut) ) {
				
				/* For each boat specified in boatBean. */
				boat.addActiveBooking(newBooking);
				
			}
			
		}
		
	}
	
	/**
	 * Set the new description.
	 * 
	 * @param description the new description.
	 */
	public void setDescription(String description) { this.description = description; }
	
	/**
	 * Retrieve the city where the rental shop is located.
	 * 
	 * @return	the city attribute.
	 */
	public String getCity() { return this.city; }

	/**
	 * Retrieve the name of the rental shop.
	 * 
	 * @return	the name attribute.
	 */
	public String getName() { return this.name; }
	
	/**
	 * Retrieve the address of the rental shop.
	 * 
	 * @return	the address attribute.
	 */
	public String getAddress() { return this.address; }
	
	/**
	 * 
	 * @return  the description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Never call this method. It needs for testing.
	 * 
	 * @return
	 */
	public List<Boat> getBoats() { return this.boats; }
	
	/**
	 * @return
	 */
	
	public String getOwner() { return this.owner;}

	/**
	 * Retrieve all bookings of an user in this rentalShop.
	 * 
	 * @param username
	 * @return			return list of bookings of this user.
	 */
	public List<Booking> getBookingsByUser(String username) {
		
		List<Booking> bookings = new ArrayList<>();
		
		for(Boat boat : this.boats) {
			
			bookings.addAll(boat.getAllBookingOfThisUser(username));
			
		}
		
		return bookings;
	}
	
	/**
	 * Return the booking that match the id.
	 * 
	 * @param id	id of the booking.
	 * @return		the booking that match the id, or null if it doesn't exist.
	 */
	public Booking getBooking(int id) {
		Booking booking = null;
		
		for(Boat boat : this.boats) {
			booking = boat.getBooking(id);
			if(booking != null)
				return booking;
			
		}
		return booking;		
	}

}
