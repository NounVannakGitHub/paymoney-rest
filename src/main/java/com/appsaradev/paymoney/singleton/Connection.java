package com.appsaradev.paymoney.singleton;

import java.sql.DriverManager;

public class Connection {

	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/paymoney";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "";
	private static java.sql.Connection connection = null;

	private Connection() {
		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			System.out.println("connected");
		} catch (Exception e) {
			System.out.println("DriverManager: " + e.getMessage());
		}
	}

	public static java.sql.Connection getSingleConnection() {
		if (connection == null) {
			new Connection();
		}
		return connection;
	}

}
