package logic.bean;

/**
 *         This class is a bean that contain data of an boat shop.
 */
public class BoatShopBean {

	/** The name of the boat shop. */
	private String name;
	/** The address of the boat shop. */
	private String address;
	/** The city where the boat shop is located. */
	private String city;
	/** The description of the boat shop. */
	private String description;
	/** The user name of the owner of the boat shop. */
	private String owner;
	/** The id of the boat shop. */
	private int id;

	public BoatShopBean(String name, String address, String city, String description, String owner, int id) {

		this.name = name;
		this.address = address;
		this.city = city;
		this.description = description;
		this.owner = owner;
		this.id = id;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
