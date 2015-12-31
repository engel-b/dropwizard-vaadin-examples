package org.bonn.se.gui.windows;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class ConfirmationWindow extends Window {
	public ConfirmationWindow(String text) {
		super("Confirmation");
		center();

		VerticalLayout content = new VerticalLayout();
		content.addComponent(new Label(text));
		content.setMargin(true);
		setContent(content);

		Button buchungsButton = new Button("OK");
		buchungsButton.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				close();
			}
		});
		content.addComponent(buchungsButton);
		content.setComponentAlignment(buchungsButton, Alignment.MIDDLE_CENTER);
	}
}
