package org.bonn.se.gui.windows;

import java.util.List;

import org.bonn.se.model.objects.dto.BookingDetail;
import org.bonn.se.process.control.BookingProcess;

import com.vaadin.data.util.BeanContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class ListBookingWindow extends Window {
	private int currentId;
	private List<BookingDetail> liste;

	public ListBookingWindow() {
		super("Liste aller Buchungen");
		center();
		VerticalLayout layout = new VerticalLayout();

		final BeanContainer<Integer, BookingDetail> data = new BeanContainer<Integer, BookingDetail>(
				BookingDetail.class);
		data.setBeanIdProperty("id");
		final Table table = new Table("Liste Ihrer Buchungen", data);
		table.setSizeFull();
		table.setSelectable(true);

		liste = BookingProcess.getInstance().getAllBookingsForUsers();

		data.addAll(liste);
		table.setPageLength(table.size());
		setSizeFull();
		layout.addComponent(table);

		Button deleteButton = new Button("Storniere Reise");
		deleteButton.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				BookingProcess.getInstance().deleteBookingById(currentId);
				data.removeAllItems();
				liste = BookingProcess.getInstance().getAllBookingsForUsers();
				data.addAll(liste);
				table.setPageLength(table.size());
			}
		});
		Label emptyLabel = new Label("&nbsp;", ContentMode.HTML);
		layout.addComponent(emptyLabel);
		layout.addComponent(deleteButton);
		layout.setComponentAlignment(deleteButton, Alignment.MIDDLE_CENTER);

		table.addItemClickListener(new ItemClickListener() {

			@Override
			public void itemClick(ItemClickEvent event) {
				currentId = (int) event.getItemId();
			}
		});

		setContent(layout);
	}

}
