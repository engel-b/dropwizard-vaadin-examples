package org.bonn.se.meinhotelapp.ui;

import java.text.MessageFormat;
import java.util.List;

import javax.servlet.annotation.WebServlet;

import org.bonn.se.model.objects.dto.Hotel;
import org.bonn.se.process.control.HotelSearch;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItem;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("valo")
@Title("MeinHotel")
@SuppressWarnings("serial")
public class MyUI extends UI {

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = MyUI.class)
	public static class Servlet extends VaadinServlet {
	}

	private Hotel hotelSelektiert = null;
	private int anzahlSuchen = 0;

	@Override
	protected void init(VaadinRequest request) {
		final VerticalLayout layout = new VerticalLayout();
		setContent(layout);

		final HorizontalLayout horizintallayout = new HorizontalLayout();
		Button button = new Button("Suche", FontAwesome.SEARCH);
		Button bucheButton = new Button("Buchen");
		TextField textField = new TextField();
		Label label = new Label("Geben Sie einen Ort ein: ");

		horizintallayout.addComponent(label);
		horizintallayout.setComponentAlignment(label, Alignment.MIDDLE_CENTER);
		horizintallayout.addComponent(textField);
		horizintallayout.addComponent(button);

		layout.addComponent(horizintallayout);
		layout.setComponentAlignment(horizintallayout, Alignment.MIDDLE_CENTER);

		BeanContainer<Integer, Hotel> data = new BeanContainer<Integer, Hotel>(Hotel.class);
		data.setBeanIdProperty("id");
		Table table = new Table("Treffer", data);
		table.setSizeFull();
		table.setSelectable(true);

		button.addClickListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				String ort = textField.getValue();
				if (ort == null || ort.isEmpty()) {
					Notification.show("Fehler", "Ort ist leer!", Type.WARNING_MESSAGE);
				} else {
					anzahlSuchen++;

					layout.addComponent(table);
					table.setCaption(
							MessageFormat.format("Treffer für {0} (Anzahl der Suchen: {1})", ort, anzahlSuchen));

					List<Hotel> hotels = HotelSearch.getHotelSearch().getHotelByOrt(ort);
					data.removeAllItems();
					data.addAll(hotels);

					table.setPageLength(data.size());

					layout.addComponent(bucheButton);
				}

			}
		});

		table.addItemClickListener(new ItemClickListener() {

			@Override
			public void itemClick(ItemClickEvent event) {
				BeanItem<Hotel> hotelItem = data.getItem(event.getItemId());
				hotelSelektiert = hotelItem.getBean();
			}
		});

		bucheButton.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				if (hotelSelektiert == null) {
					return;
				}
				// öffne Fenster zur Buchung
			}
		});

	}

}
