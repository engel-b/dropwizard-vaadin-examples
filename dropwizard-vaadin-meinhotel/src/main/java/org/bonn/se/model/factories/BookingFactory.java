package org.bonn.se.model.factories;

import java.util.Date;

import org.bonn.se.model.objects.dto.BookingRequest;
import org.bonn.se.model.objects.dto.User;
import org.bonn.se.model.objects.entities.Booking;

public class BookingFactory {
	public static Booking createBooking(BookingRequest request, User user) {
		Booking booking = new Booking();

		booking.setAnreise(request.getAnreise());
		booking.setAbreise(request.getAbreise());
		booking.setHotel(request.getHotel());
		booking.setNumber(request.getNumber());
		booking.setIban(request.getIban());

		booking.setUser(user);
		booking.setDatumBuchung(new Date());

		return booking;
	}
}
