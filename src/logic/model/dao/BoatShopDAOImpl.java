package logic.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import logic.bean.BoatShopBean;
import logic.model.BoatShop;

public class BoatShopDAOImpl implements BoatShopDAO {
	/** Query for creating a new boat shop. */
	private static final String CREATE_QUERY = "INSERT INTO boatShop (name, address, city, description, owner) VALUE (?, ?, ?, ?, ?)";
	/** Query for reading one boatShop. */
	private static final String READ_QUERY = "SELECT * FROM boatShop WHERE id = ?";
	/** Query for reading all boatShop placed in a city. */
	private static final String READ_BY_CITY_QUERY = "SELECT * FROM boatShop WHERE city = ?";
	/** Query for reading all the boatShop of an owner. */
	private static final String READ_BY_OWNER_QUERY = "SELECT * FROM boatShop WHERE owner = ?";
	/** Query for updating the fields of an boatShop. */
	private static final String UPDATE_QUERY = "UPDATE boatShop SET name = ?, address = ?, city = ?, description = ?, owner = ? WHERE id = ?";

	@Override
	public List<BoatShopBean> getAllBoatShopByCity(String city) {
		List<BoatShopBean> boatShops = new ArrayList<>();
		BoatShopBean boatShop = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = DatabaseConnection.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(READ_BY_CITY_QUERY);
			preparedStatement.setString(1, city);
			preparedStatement.execute();
			resultSet = preparedStatement.getResultSet();

			while (resultSet.next()) {
				boatShop = new BoatShopBean(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getString(5), resultSet.getInt(6));
				boatShops.add(boatShop);
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

		return boatShops;
	}

	@Override
	public BoatShopBean getBoatShop(int id) {
		BoatShopBean boatShop = null;
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
				boatShop = new BoatShopBean(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getString(5), resultSet.getInt(6));
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

		return boatShop;
	}

	@Override
	public int createBoatShop(BoatShopBean boatShop) {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		ResultSet result = null;
		try {
			conn = DatabaseConnection.getInstance().getConnection();
			preparedStatement = conn.prepareStatement(CREATE_QUERY, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, boatShop.getName());
			preparedStatement.setString(2, boatShop.getAddress());
			preparedStatement.setString(3, boatShop.getCity());
			preparedStatement.setString(4, boatShop.getDescription());
			preparedStatement.setString(5, boatShop.getOwner());
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
	public boolean updateBoatShop(BoatShopBean boatShop) {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		try {
			conn = DatabaseConnection.getInstance().getConnection();
			preparedStatement = conn.prepareStatement(UPDATE_QUERY);
			preparedStatement.setString(1, boatShop.getName());
			preparedStatement.setString(2, boatShop.getAddress());
			preparedStatement.setString(3, boatShop.getCity());
			preparedStatement.setString(4, boatShop.getDescription());
			preparedStatement.setString(5, boatShop.getOwner());
			preparedStatement.setInt(6, boatShop.getId());

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

	@Override
	public BoatShop deleteBoatShop(BoatShop boatShop) {
		return null;
	}

	@Override
	public List<BoatShopBean> getAllBoatShopByOwner(String owner) {
		List<BoatShopBean> boatShops = new ArrayList<>();
		BoatShopBean boatShop = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = DatabaseConnection.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(READ_BY_OWNER_QUERY);
			preparedStatement.setString(1, owner);
			preparedStatement.execute();
			resultSet = preparedStatement.getResultSet();

			while (resultSet.next()) {
				boatShop = new BoatShopBean(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getString(5), resultSet.getInt(6));
				boatShops.add(boatShop);
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

		return boatShops;
	}

}
