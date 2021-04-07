package logic.model;

/**
 *         Bean class that represent a person.
 */
public class Person {

	/**
	 * The fiscal code of the person.
	 */
	private String fiscalCode;

	/**
	 * The name of the person.
	 */
	private String name;

	/**
	 * The last name of the person.
	 */
	private String lastname;

	/** The id of the person. */
	private int id;

	/**
	 * Constructor of the class.
	 * 
	 * @param fiscalCode the fiscal code
	 * @param name       the name
	 * @param lastname   the last name
	 */
	public Person(String fiscalCode, String name, String lastname, int id) {

		this(fiscalCode, name, lastname);

		this.setId(id);

	}

	public Person(String fiscalCode, String name, String lastname) {

		this.fiscalCode = fiscalCode;

		this.name = name;

		this.lastname = lastname;
	}

	public Person() {}
	
	/**
	 * Get the fiscal code.
	 * 
	 * @return the fiscal code attribute.
	 */
	public String getFiscalCode() {
		return fiscalCode;
	}

	/**
	 * Set the fiscal code.
	 * 
	 * @param fiscalCode the new fiscal code.
	 */
	public void setFiscalCode(String fiscalCode) {
		this.fiscalCode = fiscalCode;
	}

	/**
	 * Get the name.
	 * 
	 * @return the name attribute.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Set the name.
	 * 
	 * @param name the new name.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get the last name.
	 * 
	 * @return the last name attribute.
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * Set the last name.
	 * 
	 * @param lastname the new last name
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
