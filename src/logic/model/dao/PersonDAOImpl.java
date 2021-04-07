package logic.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import logic.model.Person;

public class PersonDAOImpl implements PersonDAO {

	private static final String CREATE_QUERY = "INSERT INTO person (fiscal_code, first_name, last_name, booking_id) VALUES (?, ?, ?, ?)";
	/** Query for reading all people contained in a booking. */
	private static final String READ_ALL_PEOPLE_BY_BOOKING_ID_QUERY = "SELECT p.fiscal_code, p.first_name, p.last_name, p.id "
			+ "FROM person p INNER JOIN booking b ON p.booking_id = b.id" + " WHERE b.id = ?";

	@Override
	public List<Person> getAllPeopleOfABooking(int bookingId) {
		List<Person> people = new ArrayList<>();
		Person person = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = DatabaseConnection.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(READ_ALL_PEOPLE_BY_BOOKING_ID_QUERY);
			preparedStatement.setInt(1, bookingId);
			preparedStatement.execute();
			resultSet = preparedStatement.getResultSet();

			while (resultSet.next()) {
				person = new Person(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getInt(4));
				people.add(person);
			}
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				resultSet.close();
			} catch (Exception rse) {
				rse.printStackTrace();
			}
			try {
				preparedStatement.close();
			} catch (Exception sse) {
				sse.printStackTrace();
			}

		}

		return people;
	}

	@Override
	public int createPerson(Person person, int idBooking) {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		ResultSet result = null;
		try {
			conn = DatabaseConnection.getInstance().getConnection();
			preparedStatement = conn.prepareStatement(CREATE_QUERY, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, person.getFiscalCode());
			preparedStatement.setString(2, person.getName());
			preparedStatement.setString(3, person.getLastname());
			preparedStatement.setInt(4, idBooking);
			preparedStatement.execute();
			result = preparedStatement.getGeneratedKeys();

			if (result.next()) {
				return result.getInt(1);
			} else {
				return -1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				result.close();
			} catch (Exception rse) {
				rse.printStackTrace();
			}
			try {
				preparedStatement.close();
			} catch (Exception sse) {
				sse.printStackTrace();
			}

		}

		return -1;
	}

}
