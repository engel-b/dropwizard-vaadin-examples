package org.bonn.se.process.control;

import org.bonn.se.process.control.exceptions.NoSuchUserOrPasswortException;
import org.bonn.se.services.util.Views;

import com.vaadin.ui.UI;

public class LoginControl {
	public static void checkAuthentication(String login, String passwort) throws NoSuchUserOrPasswortException {
		// TODO

		// wenn alles ok
		UI.getCurrent().getNavigator().navigateTo(Views.MAIN);

		// Fehlerfall
		throw new NoSuchUserOrPasswortException();
	}
}
