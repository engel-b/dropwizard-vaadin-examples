package org.bonn.se.gui.windows;

import java.util.Date;

import org.bonn.se.model.objects.dto.BookingRequest;
import org.bonn.se.model.objects.dto.Hotel;
import org.bonn.se.process.control.BookingProcess;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class BookingWindow extends Window {

	public BookingWindow(final Hotel hotel) {
		super("Buchung");
		center();

		// some basic content for the window
		VerticalLayout content = new VerticalLayout();
		content.addComponent(new Label("Buchung für Hotel " + hotel.getName()));
		content.setMargin(true);
		setContent(content);

		final DateField dateAnreise = new DateField();
		content.addComponent(dateAnreise);
		dateAnreise.setCaption("Anreise: ");
		dateAnreise.setDateFormat("dd.MM.yyyy");
		dateAnreise.setValue(new Date());

		final DateField dateAbreise = new DateField();
		content.addComponent(dateAbreise);
		dateAbreise.setCaption("Abreise: ");
		dateAbreise.setDateFormat("dd.MM.yyyy");
		dateAbreise.setValue(new Date());

		final ComboBox personNumber = new ComboBox();
		personNumber.setCaption("Anzahl Personen: ");
		content.addComponent(personNumber);
		personNumber.addItem(new Integer(1));
		personNumber.addItem(new Integer(2));
		personNumber.addItem(new Integer(3));
		personNumber.addItem(new Integer(4));
		personNumber.addItem(new Integer(5));
		personNumber.addItem(new Integer(6));

		final TextField ibanFeld = new TextField();
		ibanFeld.setCaption("IBAN: ");
		content.addComponent(ibanFeld);

		Label emptyLabel = new Label("&nbsp;", ContentMode.HTML);
		content.addComponent(emptyLabel);

		setClosable(true);

		Button buchungButton = new Button("Buche");
		buchungButton.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				BookingRequest request = new BookingRequest();
				request.setAnreise(dateAnreise.getValue());
				request.setAbreise(dateAbreise.getValue());
				request.setIban(ibanFeld.getValue());
				request.setNumber((Integer) personNumber.getValue());
				request.setHotel(hotel);

				BookingProcess.getInstance().createBooking(request, BookingWindow.this);

			}
		});
		content.addComponent(buchungButton);
		content.setComponentAlignment(buchungButton, Alignment.MIDDLE_CENTER);
	}
}
