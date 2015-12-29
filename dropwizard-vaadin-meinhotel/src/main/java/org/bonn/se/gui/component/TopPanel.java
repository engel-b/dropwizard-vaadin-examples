package org.bonn.se.gui.component;

import org.bonn.se.model.objects.dto.User;
import org.bonn.se.process.control.LoginControl;
import org.bonn.se.services.util.Roles;

import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.UI;

public class TopPanel extends HorizontalLayout {
	public TopPanel() {
		setSizeFull();

		Label headLabel = new Label("MeinHotel - <i>das Reservierungssystem</i>", ContentMode.HTML);
		headLabel.setSizeUndefined();
		headLabel.setStyleName("mytitle");
		addComponent(headLabel);
		setComponentAlignment(headLabel, Alignment.TOP_LEFT);

		HorizontalLayout horizontalLayout = new HorizontalLayout();

		User user = (User) UI.getCurrent().getSession().getAttribute(Roles.CURRENT_USER);

		Label loggedLabel = new Label("Welcome " + user.getVorname() + "!");
		loggedLabel.setSizeUndefined();
		loggedLabel.setStyleName("loggedLabel");

		horizontalLayout.addComponent(loggedLabel);
		horizontalLayout.setComponentAlignment(loggedLabel, Alignment.MIDDLE_CENTER);

		MenuBar menuBar = new MenuBar();
		MenuItem item1 = menuBar.addItem("Menu", null);

		// TODO Logout des Users
		item1.addItem("Logout", FontAwesome.SIGN_OUT, new Command() {

			@Override
			public void menuSelected(MenuItem selectedItem) {
				LoginControl.logoutUser();
			}
		});

		// Stornierung von Reisen
		item1.addItem("Cancel", FontAwesome.UNLINK, null);

		horizontalLayout.addComponent(menuBar);
		addComponent(horizontalLayout);
		setComponentAlignment(horizontalLayout, Alignment.TOP_RIGHT);
	}
}
