package org.bonn.se.services.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.bonn.se.process.control.exceptions.DatabaseException;

public class JDBCConnection {
	private static JDBCConnection jdbcConnection = null;

	public static JDBCConnection getInstance() throws DatabaseException {
		if (jdbcConnection == null) {
			jdbcConnection = new JDBCConnection();
		}
		return jdbcConnection;
	}

	private Connection connection;

	String jdbcUrl = "jdbc:h2:./target/example";

	private JDBCConnection() throws DatabaseException {
		initJDBCConnection();
	}

	public void closeConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public PreparedStatement getPreparedStatement(String sql) throws DatabaseException {
		try {
			if (connection.isClosed()) {
				openConnection();
			}
			return connection.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Statement getStatement() throws DatabaseException {
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

	private void initJDBCConnection() throws DatabaseException {
		try {
			DriverManager.registerDriver(new org.h2.Driver());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		openConnection();
	}

	public void openConnection() throws DatabaseException {
		try {
			Properties props = new Properties();
			props.setProperty("user", "sa");
			props.setProperty("password", "sa");

			connection = DriverManager.getConnection(jdbcUrl, props);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatabaseException("Fehler beim Zugriff auf die Datenbank.");
		}
	}
}
