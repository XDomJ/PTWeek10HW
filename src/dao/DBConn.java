package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConn {

	private final static String dbURL = "jdbc:mysql://localhost:3306/guitar_db";
	private static Connection connection;
	private static DBConn instance;
	
	
	private DBConn(Connection connection) {
		this.connection = connection;
	}
	
	public static Connection getConnection() {
		if (instance == null) {
			try {
				connection = DriverManager.getConnection(dbURL, "root", "PonyX7!Icem4n");
				instance = new DBConn(connection);
				System.out.println("Successfully connected!");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return DBConn.connection;
	}
}
