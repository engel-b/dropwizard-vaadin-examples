package org.bonn.se.process.control;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.bonn.se.gui.ui.MyUI;
import org.bonn.se.model.objects.dto.User;
import org.bonn.se.process.control.exceptions.DatabaseException;
import org.bonn.se.process.control.exceptions.NoSuchUserOrPasswortException;
import org.bonn.se.services.db.JDBCConnection;
import org.bonn.se.services.util.Views;

import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;

public class LoginControl {
	public static void checkAuthentication(String login, String passwort)
			throws NoSuchUserOrPasswortException, DatabaseException {
		ResultSet set = null;
		try {
			Statement statement = JDBCConnection.getInstance().getStatement();
			set = statement.executeQuery(
					"select * from user where login = \'" + login + "\' and passwort = \'" + passwort + "\'");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatabaseException("Fehler in SQL-Statement");
		}

		User user = null;

		try {
			user = new User();
			if (set != null && set.next()) {
				user.setLogin(set.getString(1));
				user.setVorname(set.getString(3));
			} else {
				throw new NoSuchUserOrPasswortException();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCConnection.getInstance().closeConnection();
		}
		// wenn alles ok
		VaadinSession session = UI.getCurrent().getSession();
		((MyUI) UI.getCurrent()).setUser(user);
		UI.getCurrent().getNavigator().navigateTo(Views.MAIN);
	}

	public static void logoutUser() {
		UI.getCurrent().close();
		UI.getCurrent().getPage().setLocation("/vaadin");
	}
}
