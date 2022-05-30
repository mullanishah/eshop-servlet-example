package com.tasha.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Shahrukh
 *
 */
public class DatabaseUtils {
	private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost/eshop-db";
	private static final String DB_USERNAME = "root";
	private static final String DB_PASSWORD = "root";
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		
		Class.forName(DB_DRIVER);
		Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
		return conn;
	}
}
