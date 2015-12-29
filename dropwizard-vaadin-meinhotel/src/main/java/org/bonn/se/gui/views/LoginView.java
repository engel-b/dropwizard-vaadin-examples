package org.bonn.se.gui.views;

import org.bonn.se.model.objects.dto.User;
import org.bonn.se.process.control.LoginControl;
import org.bonn.se.process.control.exceptions.DatabaseException;
import org.bonn.se.process.control.exceptions.NoSuchUserOrPasswortException;
import org.bonn.se.services.util.Roles;
import org.bonn.se.services.util.Views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class LoginView extends VerticalLayout implements View {

	@Override
	public void enter(ViewChangeEvent event) {
		User user = (User) UI.getCurrent().getSession().getAttribute(Roles.CURRENT_USER);

		if (user != null) {
			UI.getCurrent().getNavigator().navigateTo(Views.MAIN);
		} else {
			setUp();
		}
	}

	public void setUp() {
		Label willkommenLabel = new Label("Bitte einloggen:");

		TextField userlogin = new TextField("UserID:");
		PasswordField passwortField = new PasswordField("Passwort:");
		Button loginButton = new Button("Login", FontAwesome.KEY);

		loginButton.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				try {
					LoginControl.checkAuthentication(userlogin.getValue(), passwortField.getValue());
				} catch (NoSuchUserOrPasswortException e) {
					Notification.show("Benutzerfehler", "Benutzername oder Passwort falsch", Type.ERROR_MESSAGE);
					userlogin.setValue("");
					passwortField.setValue("");
				} catch (DatabaseException e) {
					Notification.show("Datenbankfehler", e.getReason(), Type.ERROR_MESSAGE);
				}
			}
		});

		VerticalLayout layout = new VerticalLayout();
		layout.addComponent(willkommenLabel);
		layout.addComponent(userlogin);
		layout.addComponent(passwortField);
		layout.addComponent(loginButton);

		Panel panel = new Panel("Login");
		panel.addStyleName("login");
		panel.setContent(layout);
		panel.setSizeUndefined();

		addComponent(panel);
		setComponentAlignment(panel, Alignment.MIDDLE_CENTER);
		setSizeFull();
	}
}
