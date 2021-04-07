package logic.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import logic.bean.BookingBean;
import logic.model.bookingState.StateEnum;

public class BookingDAOImpl implements BookingDAO {

	private static final Logger LOGGER = Logger.getLogger( BookingDAOImpl.class.getName() );
	
	/** Query for creating a new booking. */
	private static final String CREATE_QUERY = "INSERT INTO booking (check_in, check_out, state, user,  boat_id) VALUES (?, ?, ?, ?, ?)";
	/** Query for reading the booking. */
	private static final String READ_QUERY = "SELECT check_in, check_out, state, user, id FROM booking WHERE id = ?";
	/** Query for reading all booking of a specific boat. */
	private static final String READ_ALL_BY_BOAT_ID_QUERY = "SELECT h.name, " + "b.check_in, " + "b.check_out, "
			+ "b.state, " + "b.user, " + "b.id " + "FROM boatShop h " + "INNER JOIN boat r " + "ON h.id = r.boatShop_id "
			+ "INNER JOIN booking b " + "ON r.id = b.boat_id " + "WHERE b.boat_id = ?";
	/** Query for reading all booking of an user. */
	private static final String READ_ALL_BY_USER_QUERY = "SELECT h.name, " + "b.check_in, " + "b.check_out, "
			+ "b.state, " + "b.user, " + "b.id " + "FROM boatShop h " + "INNER JOIN boat r " + "ON h.id = r.boatShop_id "
			+ "INNER JOIN booking b " + "ON r.id = b.boat_id " + "WHERE b.user = ?";
	/** Query for updating the booking. */
	private static final String UPDATE_QUERY = "UPDATE booking SET state = ? WHERE id = ?";

	@Override
	public List<BookingBean> getAllBookingOfABoat(int boatId) {
		List<BookingBean> bookings = new ArrayList<>();
		BookingBean booking = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = DatabaseConnection.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(READ_ALL_BY_BOAT_ID_QUERY);
			preparedStatement.setInt(1, boatId);
			preparedStatement.execute();
			resultSet = preparedStatement.getResultSet();

			while (resultSet.next()) {
				booking = new BookingBean(resultSet.getString(1), resultSet.getDate(2).toLocalDate(),
						resultSet.getDate(3).toLocalDate(), StateEnum.valueOf(resultSet.getString(4)),
						resultSet.getString(5), resultSet.getInt(6));
				bookings.add(booking);
			}
		} catch (SQLException e) {
			LOGGER.log( Level.SEVERE, e.toString(), e );

		} finally {
			try {
				resultSet.close();
			} catch (Exception rse) {
				LOGGER.log( Level.SEVERE, rse.toString(), rse );
			}
			try {
				preparedStatement.close();
			} catch (Exception sse) {
				LOGGER.log( Level.SEVERE, sse.toString(), sse );
			}

		}

		return bookings;
	}

	@Override
	public List<BookingBean> getAllBookingOfAUser(String username) {
		List<BookingBean> bookings = new ArrayList<>();
		BookingBean booking = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = DatabaseConnection.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(READ_ALL_BY_USER_QUERY);
			preparedStatement.setString(1, username);
			preparedStatement.execute();
			resultSet = preparedStatement.getResultSet();

			while (resultSet.next()) {
				booking = new BookingBean(resultSet.getString(1), resultSet.getDate(2).toLocalDate(),
						resultSet.getDate(3).toLocalDate(), StateEnum.valueOf(resultSet.getString(4)),
						resultSet.getString(5), resultSet.getInt(6));
				bookings.add(booking);
			}
		} catch (SQLException e) {
			LOGGER.log( Level.SEVERE, e.toString(), e );

		} finally {
			try {
				resultSet.close();
			} catch (Exception rse) {
				LOGGER.log( Level.SEVERE, rse.toString(), rse );
			}
			try {
				preparedStatement.close();
			} catch (Exception sse) {
				LOGGER.log( Level.SEVERE, sse.toString(), sse );
			}

		}

		return bookings;
	}

	@Override
	public int createBooking(BookingBean booking, int boatId) {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		ResultSet result = null;
		try {
			conn = DatabaseConnection.getInstance().getConnection();
			preparedStatement = conn.prepareStatement(CREATE_QUERY, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setDate(1, Date.valueOf(booking.getCheckIn()));
			preparedStatement.setDate(2, Date.valueOf(booking.getCheckOut()));
			preparedStatement.setString(3, String.valueOf(booking.getState()));
			preparedStatement.setString(4, booking.getUser());
			preparedStatement.setInt(5, boatId);
			preparedStatement.execute();
			result = preparedStatement.getGeneratedKeys();

			if (result.next()) {
				return result.getInt(1);
			} else {
				return -1;
			}
		} catch (SQLException e) {
			LOGGER.log( Level.SEVERE, e.toString(), e );
		} finally {
			try {
				result.close();
			} catch (Exception rse) {
				LOGGER.log( Level.SEVERE, rse.toString(), rse );
			}
			try {
				preparedStatement.close();
			} catch (Exception sse) {
				LOGGER.log( Level.SEVERE, sse.toString(), sse );
			}

		}

		return -1;
	}

	@Override
	public boolean updateBooking(BookingBean booking) {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		try {
			conn = DatabaseConnection.getInstance().getConnection();
			preparedStatement = conn.prepareStatement(UPDATE_QUERY);
			preparedStatement.setString(1, String.valueOf(booking.getState()));
			preparedStatement.setInt(2, booking.getBookingId());

			preparedStatement.execute();
			return true;
		} catch (SQLException e) {
			LOGGER.log( Level.SEVERE, e.toString(), e );
		} finally {
			try {
				preparedStatement.close();
			} catch (Exception sse) {
				LOGGER.log( Level.SEVERE, sse.toString(), sse );
			}
		}
		return false;
	}

	@Override
	public BookingBean getBooking(int id) {
		BookingBean booking = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = DatabaseConnection.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(READ_QUERY);
			preparedStatement.setInt(1, id);
			preparedStatement.execute();
			resultSet = preparedStatement.getResultSet();

			if (resultSet.next()) {
				booking = new BookingBean();
				booking.setCheckIn(resultSet.getDate(1).toLocalDate());
				booking.setCheckOut(resultSet.getDate(2).toLocalDate());
				booking.setState(StateEnum.valueOf(resultSet.getString(3)));
				booking.setUser(resultSet.getString(4));
				booking.setBookingId(resultSet.getInt(5));
			}
		} catch (SQLException e) {
			LOGGER.log( Level.SEVERE, e.toString(), e );

		} finally {
			try {
				resultSet.close();
			} catch (Exception rse) {
				LOGGER.log( Level.SEVERE, rse.toString(), rse );
			}
			try {
				preparedStatement.close();
			} catch (Exception sse) {
				LOGGER.log( Level.SEVERE, sse.toString(), sse );
			}
		}

		return booking;
	}

}
