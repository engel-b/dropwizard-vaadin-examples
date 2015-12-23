package org.bonn.se.gui.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class LoginView extends VerticalLayout implements View {

	@Override
	public void enter(ViewChangeEvent event) {
		setUp();
	}

	public void setUp() {
		Label label = new Label("Hello login");
		addComponent(label);
	}
}
