package logic.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDatabase {
	
	private static final String CREATE_DATABASE_QUERY = "CREATE SCHEMA IF NOT EXISTS `boat_to_go` DEFAULT CHARACTER SET utf8 ;";
	
	private static final String CREATE_TABLE_BOAT_SHOP = "CREATE TABLE IF NOT EXISTS `boat_to_go`.`boatShop` (\r\n" + 
			"  `name` VARCHAR(45) NOT NULL,\r\n" + 
			"  `address` VARCHAR(45) NOT NULL,\r\n" + 
			"  `city` VARCHAR(45) NOT NULL,\r\n" + 
			"  `description` VARCHAR(500) NOT NULL,\r\n" + 
			"  `owner` VARCHAR(45) NOT NULL,\r\n" + 
			"  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,\r\n" + 
			"  PRIMARY KEY (`id`))\r\n" + 
			"ENGINE = InnoDB;";
	
	private static final String CREATE_TABLE_BOAT = "CREATE TABLE IF NOT EXISTS `boat_to_go`.`boat` (\r\n" + 
			"  `description` VARCHAR(500) NOT NULL,\r\n" + 
			"  `seats` SMALLINT NOT NULL,\r\n" + 
			"  `size` SMALLINT NOT NULL,\r\n" + 
			"  `toilets` SMALLINT NOT NULL,\r\n" + 
			"  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,\r\n" + 
			"  `boatShop_id` BIGINT UNSIGNED NOT NULL,\r\n" + 
			"  PRIMARY KEY (`id`, `boatShop_id`),\r\n" + 
			"  INDEX `fk_room_boatShop_idx` (`boatShop_id` ASC) VISIBLE,\r\n" + 
			"  CONSTRAINT `fk_boat_hotel`\r\n" + 
			"    FOREIGN KEY (`boatShop_id`)\r\n" + 
			"    REFERENCES `boat_to_go`.`boatShop` (`id`)\r\n" + 
			"    ON DELETE NO ACTION\r\n" + 
			"    ON UPDATE NO ACTION)\r\n" + 
			"ENGINE = InnoDB;";
	
	private static final String CREATE_TABLE_BOOKING = "CREATE TABLE IF NOT EXISTS `boat_to_go`.`booking` (\r\n" + 
			"  `check_in` DATE NOT NULL,\r\n" + 
			"  `check_out` DATE NOT NULL,\r\n" + 
			"  `state` ENUM('SUBMITTED', 'ACCEPTED', 'DELETED', 'INACTIVE') NOT NULL,\r\n" + 
			"  `user` VARCHAR(45) NOT NULL,\r\n" + 
			"  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,\r\n" + 
			"  `boat_id` BIGINT UNSIGNED NOT NULL,\r\n" + 
			"  PRIMARY KEY (`id`),\r\n" + 
			"  INDEX `fk_booking_boat1_idx` (`boat_id` ASC) VISIBLE,\r\n" + 
			"  CONSTRAINT `fk_booking_boat1`\r\n" + 
			"    FOREIGN KEY (`boat_id`)\r\n" + 
			"    REFERENCES `boat_to_go`.`boat` (`id`)\r\n" + 
			"    ON DELETE NO ACTION\r\n" + 
			"    ON UPDATE NO ACTION)\r\n" + 
			"ENGINE = InnoDB;";
	
	private static final String CREATE_TABLE_PERSON = "CREATE TABLE IF NOT EXISTS `boat_to_go`.`Person` (\r\n" + 
			"  `fiscal_code` VARCHAR(16) NOT NULL,\r\n" + 
			"  `first_name` VARCHAR(45) NOT NULL,\r\n" + 
			"  `last_name` VARCHAR(45) NOT NULL,\r\n" + 
			"  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,\r\n" + 
			"  `booking_id` BIGINT UNSIGNED NOT NULL,\r\n" + 
			"  PRIMARY KEY (`id`, `booking_id`),\r\n" + 
			"  INDEX `fk_Person_booking1_idx` (`booking_id` ASC) VISIBLE,\r\n" + 
			"  CONSTRAINT `fk_Person_booking1`\r\n" + 
			"    FOREIGN KEY (`booking_id`)\r\n" + 
			"    REFERENCES `boat_to_go`.`booking` (`id`)\r\n" + 
			"    ON DELETE NO ACTION\r\n" + 
			"    ON UPDATE NO ACTION)\r\n" + 
			"ENGINE = InnoDB;";
	
	private CreateDatabase() {}
	
	private static final void createDatabase() {		
		String jdbcDriver = "com.mysql.cj.jdbc.Driver";
		String dbUrl = "jdbc:mysql://localhost?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		
		String user = "root";
		String password = "password";
		
		Connection conn = null;
		Statement stmt = null;
		
		try {
			Class.forName(jdbcDriver);
			conn = DriverManager.getConnection(dbUrl, user, password);
			stmt = conn.createStatement();
			stmt.executeUpdate(CREATE_DATABASE_QUERY);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if(stmt != null)
					stmt.close();
			} catch(SQLException se2) {
				se2.printStackTrace();
			}
			try {
				if(conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}
	
	private static final void executeQuery(String query) {
		Connection conn = null;
		Statement stmt = null;
		
		try {
			conn = DatabaseConnection.getInstance().getConnection();
			stmt = conn.createStatement();
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(stmt != null)
					stmt.close();
			} catch(SQLException se2) {
				se2.printStackTrace();
			}
		}
	}
	
	public static final void createTables() {
		CreateDatabase.createDatabase();
		
		CreateDatabase.executeQuery(CREATE_TABLE_BOAT_SHOP);
		CreateDatabase.executeQuery(CREATE_TABLE_BOAT);
		CreateDatabase.executeQuery(CREATE_TABLE_BOOKING);
		CreateDatabase.executeQuery(CREATE_TABLE_PERSON);
		
		String boatParadise = "INSERT INTO `boat_to_go`.`boatShop` (`name`, `address`, `city`, `description`, `owner`, `id`) VALUES ('Boat Paradise', 'Viale Totti 10', 'Roma', 'Rental shop of boats', 'owner', 1);";
		String seaLovers = "INSERT INTO `boat_to_go`.`boatShop` (`name`, `address`, `city`, `description`, `owner`, `id`) VALUES ('Sea Lovers', 'Piazzale De Rossi 16', 'Trigoria', 'Rental shop of canoes', 'owner', 2);";
		
		String boat1 = "INSERT INTO `boat_to_go`.`boat` (`description`, `seats`, `size`, `toilets`, `id`, `boatShop_id`) VALUES ('Barca con 12 posti a sedere e 1 bagno', 12, 20, 1, 1, 1);";
		String boat2 = "INSERT INTO `boat_to_go`.`boat` (`description`, `seats`, `size`, `toilets`, `id`, `boatShop_id`) VALUES ('Barca con 15 posti a sedere e 1 bagno', 15, 25, 1, 2, 1);";
		String boat3 = "INSERT INTO `boat_to_go`.`boat` (`description`, `seats`, `size`, `toilets`, `id`, `boatShop_id`) VALUES ('Barca con 20 posti a sedere e 1 bagno', 20, 30, 1, 3, 1);";
		String boat4 = "INSERT INTO `boat_to_go`.`boat` (`description`, `seats`, `size`, `toilets`, `id`, `boatShop_id`) VALUES ('Barca con 25 posti a sedere e 2 bagni', 25, 35, 2, 4, 1);";
		String boat5 = "INSERT INTO `boat_to_go`.`boat` (`description`, `seats`, `size`, `toilets`, `id`, `boatShop_id`) VALUES ('Canoa con 3 posti a sedere', 3, 40, 2, 5, 2);";
		String boat6 = "INSERT INTO `boat_to_go`.`boat` (`description`, `seats`, `size`, `toilets`, `id`, `boatShop_id`) VALUES ('Kayak eschimese con 2 posti a sedere', 2, 20, 1, 6, 2);";
		
		
		CreateDatabase.executeQuery(boatParadise);
		CreateDatabase.executeQuery(seaLovers);
		CreateDatabase.executeQuery(boat1);
		CreateDatabase.executeQuery(boat2);
		CreateDatabase.executeQuery(boat3);
		CreateDatabase.executeQuery(boat4);
		CreateDatabase.executeQuery(boat5);
		CreateDatabase.executeQuery(boat6);
	}

}
