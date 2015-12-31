package org.bonn.se.model.dao;

import java.sql.PreparedStatement;
import java.sql.Statement;

import org.bonn.se.process.control.exceptions.DatabaseException;
import org.bonn.se.services.db.JDBCConnection;

public class AbstractDAO<E> {
	// CRUD
	protected void create(E e) {

	}

	protected PreparedStatement getPreparedStatement(String sql) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = JDBCConnection.getInstance().getPreparedStatement(sql);
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
		return preparedStatement;
	}

	protected Statement getStatement() {
		Statement statement = null;
		try {
			statement = JDBCConnection.getInstance().getStatement();
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
		return statement;
	}
}
