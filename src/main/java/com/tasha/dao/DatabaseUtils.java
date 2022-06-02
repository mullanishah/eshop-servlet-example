package com.tasha.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Shahrukh
 * @since May 2022
 */
public class DatabaseUtils {
	private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost/eshop-db";
	private static final String DB_USERNAME = "root";
	private static final String DB_PASSWORD = "root";
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		
		Class.forName(DB_DRIVER);
		System.out.println("Drivers got loaded");
		Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
		System.out.println("connection to db made successfully");
		return conn;
	}
}
