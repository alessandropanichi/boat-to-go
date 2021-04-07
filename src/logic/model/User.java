package logic.model;

/**
 *         This class represent the user in the system.
 */
public class User {

	private String username;

	private String password;

	private String name;

	private String lastname;

	/**
	 * Constructor.
	 * 
	 * @param username username.
	 * @param password password.
	 * @param name     name.
	 * @param lastname lastname.
	 */
	public User(String username, String password, String name, String lastname) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.lastname = lastname;
	}

	public User() {
		this.username = "";
		this.password = "";
		this.name = "";
		this.lastname = "";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

}
