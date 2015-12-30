package org.bonn.se.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.bonn.se.model.objects.dto.Role;
import org.bonn.se.model.objects.dto.User;
import org.bonn.se.process.control.exceptions.DatabaseException;
import org.bonn.se.services.db.JDBCConnection;

public class RoleDAO {
	private static RoleDAO dao = null;

	public static RoleDAO getInstance() {
		if (dao == null) {
			dao = new RoleDAO();
		}
		return dao;
	}

	private RoleDAO() {
	}

	public List<Role> getRolesForUser(User user) {
		Statement statement = null;

		try {
			statement = JDBCConnection.getInstance().getStatement();
		} catch (DatabaseException e) {
			e.printStackTrace();
			return null;
		}
		ResultSet resultSet = null;

		try {
			resultSet = statement.executeQuery("SELECT * FROM user_to_rolle WHERE login = \'" + user.getLogin() + "\'");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (resultSet == null) {
			return null;
		}

		List<Role> liste = new ArrayList<Role>();
		Role role = null;

		try {
			while (resultSet.next()) {
				role = new Role();
				role.setBezeichnung(resultSet.getString(2));
				liste.add(role);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return liste;
	}
}
