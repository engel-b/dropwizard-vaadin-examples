package org.bonn.se.process.control;

import org.bonn.se.gui.ui.MyUI;
import org.bonn.se.gui.windows.ConfirmationWindow;
import org.bonn.se.model.dao.BuchungDAO;
import org.bonn.se.model.factories.BookingFactory;
import org.bonn.se.model.objects.dto.BookingRequest;
import org.bonn.se.model.objects.dto.User;
import org.bonn.se.model.objects.entities.Booking;

import com.vaadin.ui.UI;
import com.vaadin.ui.Window;

public class BookingProcess {
	public static BookingProcess bookingProcess = null;

	public static BookingProcess getInstance() {
		if (bookingProcess == null) {
			bookingProcess = new BookingProcess();
		}
		return bookingProcess;
	}

	private BookingProcess() {
	}

	public void createBooking(BookingRequest bookingRequest, Window window) {
		User user = ((MyUI) UI.getCurrent()).getUser();
		Booking booking = BookingFactory.createBooking(bookingRequest, user);
		if (BuchungDAO.getInstance().addBooking(booking)) {
			window.close();
			UI.getCurrent().addWindow(new ConfirmationWindow("Buchung erfolgreich! Id: " + booking.getId()));
		}
	}

	public void deleteBookingById(int id) {
		BuchungDAO.getInstance().deleteBookingBy(id);
		UI.getCurrent().addWindow(new ConfirmationWindow("Die Reise wurde storniert."));
	}
}
