package org.bonn.se.services.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCConnection {
	private static JDBCConnection jdbcConnection = null;

	public static JDBCConnection getInstance() {
		if (jdbcConnection == null) {
			jdbcConnection = new JDBCConnection();
		}
		return jdbcConnection;
	}

	private Connection connection;

	String jdbcUrl = "jdbc:h2:./target/example";

	private JDBCConnection() {
		initJDBCConnection();
	}

	public void closeConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Statement getStatement() {
		try {
			if (connection.isClosed()) {
				openConnection();
			}
			return connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	private void initJDBCConnection() {
		try {
			DriverManager.registerDriver(new org.h2.Driver());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		openConnection();
	}

	public void openConnection() {
		try {
			Properties props = new Properties();
			props.setProperty("user", "sa");
			props.setProperty("password", "sa");

			connection = DriverManager.getConnection(jdbcUrl, props);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
