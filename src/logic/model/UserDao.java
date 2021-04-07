package logic.model;

public class UserDao {

	private UserDao() {
		throw new IllegalStateException("Utility class");
	}

	public static User findUserMock(String username, String password) {

		if (username.equals("username") && password.equals("password"))
			return new User();

		else
			return null;

	}

	public static User findOwnerMock(String username, String password) {

		if (username.equals("owner") && password.equals("password"))
			return new User();

		else
			return null;

	}

}
