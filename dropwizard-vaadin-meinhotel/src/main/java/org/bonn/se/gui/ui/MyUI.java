package org.bonn.se.gui.ui;

import javax.servlet.annotation.WebServlet;

import org.bonn.se.gui.views.LoginView;
import org.bonn.se.gui.views.MainView;
import org.bonn.se.services.util.Views;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

@Theme("valo")
@Title("MeinHotel")
@SuppressWarnings("serial")
public class MyUI extends UI {

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = MyUI.class)
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {
		Navigator navi = new Navigator(this, this);
		navi.addView(Views.MAIN, MainView.class);
		navi.addView(Views.LOGIN, LoginView.class);

		UI.getCurrent().getNavigator().navigateTo(Views.LOGIN);
	}

}
