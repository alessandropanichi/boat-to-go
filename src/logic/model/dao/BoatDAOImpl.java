package logic.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import logic.bean.BoatBean;

public class BoatDAOImpl implements BoatDAO {
	/** Query for creating a new boat. */
	private static final String CREATE_QUERY = "INSERT INTO boat (description, seats, size, toilets, boatShop_id) VALUES (?, ?, ?, ?, ?) ";
	/** Query for reading all boats of a specific boat shop */
	private static final String READ_ALL_QUERY_BY_BOAT_SHOP_ID = "SELECT boat.description, boat.seats, boat.size, boat.toilets, boat.id"
			+ " FROM boat INNER JOIN boatShop ON boat.boatShop_id = boatShop.id WHERE boatShop.id = ?";
	/** Query for updating a boat. */
	private static final String UPDATE_QUERY = "UPDATE boat SET description = ?, seats = ?, size = ?, toilets = ? WHERE id = ?";

	@Override
	public List<BoatBean> getAllBoatOfABoatShop(int boatShopId) {
		List<BoatBean> boats = new ArrayList<>();
		BoatBean boat = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = DatabaseConnection.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(READ_ALL_QUERY_BY_BOAT_SHOP_ID);
			preparedStatement.setInt(1, boatShopId);
			preparedStatement.execute();
			resultSet = preparedStatement.getResultSet();

			while (resultSet.next()) {
				boat = new BoatBean(resultSet.getString(1), resultSet.getInt(2), resultSet.getInt(3),
						resultSet.getInt(4), resultSet.getInt(5));
				boats.add(boat);
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

		return boats;
	}

	@Override
	public int createBoat(BoatBean boat, int boatShopId) {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		ResultSet result = null;
		try {
			conn = DatabaseConnection.getInstance().getConnection();
			preparedStatement = conn.prepareStatement(CREATE_QUERY, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, boat.getDescription());
			preparedStatement.setInt(2, boat.getSeats());
			preparedStatement.setInt(3, boat.getSize());
			preparedStatement.setInt(4, boat.getToilets());
			preparedStatement.setInt(5, boatShopId);
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

	@Override
	public boolean updateBoat(BoatBean boat) {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		try {
			conn = DatabaseConnection.getInstance().getConnection();
			preparedStatement = conn.prepareStatement(UPDATE_QUERY);
			preparedStatement.setString(1, boat.getDescription());
			preparedStatement.setInt(2, boat.getSeats());
			preparedStatement.setInt(3, boat.getSize());
			preparedStatement.setInt(4, boat.getToilets());
			preparedStatement.setInt(5, boat.getId());

			preparedStatement.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
			} catch (Exception sse) {
				sse.printStackTrace();
			}
			try {
				conn.close();
			} catch (Exception cse) {
				cse.printStackTrace();
			}
		}
		return false;
	}

}
