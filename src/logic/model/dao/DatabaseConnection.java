package logic.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseConnection {

	private static final Logger LOGGER = Logger.getLogger(DatabaseConnection.class.getName());
	private static DatabaseConnection instance;
	private static Connection connection;
	private static String url = "jdbc:mysql://localhost/boat_to_go?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private static String username = "root";
	private static String password = "password";

	private DatabaseConnection() throws SQLException {

		openConnection();

	}

	private static void openConnection() throws SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, username, password);

		} catch (ClassNotFoundException ex) {
			LOGGER.log(Level.SEVERE, ex.toString(), ex);
		}
	}

	public Connection getConnection() {

		return connection;
	}

	public static synchronized DatabaseConnection getInstance() throws SQLException {

		if (instance == null) {
			instance = new DatabaseConnection();

		} else if (instance.getConnection().isClosed()) {
			openConnection();
		}

		return instance;
	}

}
