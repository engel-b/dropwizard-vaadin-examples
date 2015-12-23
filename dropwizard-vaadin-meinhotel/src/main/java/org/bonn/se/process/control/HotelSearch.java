package org.bonn.se.process.control;

import java.util.ArrayList;
import java.util.List;

import org.bonn.se.model.objects.dto.Hotel;

public class HotelSearch {
	public static HotelSearch hotelSearch = null;

	public static HotelSearch getHotelSearch() {
		if (hotelSearch == null) {
			hotelSearch = new HotelSearch();
		}
		return hotelSearch;
	}

	private List<Hotel> hotelListe = null;

	private HotelSearch() {
	}

	public List<Hotel> getHotelByOrt(String ort) {
		List<Hotel> resultList = new ArrayList<Hotel>();
		for (Hotel hotel : getHotelListe()) {
			if (hotel.getOrt().contentEquals(ort)) {
				resultList.add(hotel);
			}
		}
		return resultList;
	}

	private List<Hotel> getHotelListe() {
		if (hotelListe == null) {
			hotelListe = new ArrayList<Hotel>();
			hotelListe.add(new Hotel("Hotel1", 1, "Bonn", "Sehr schönes Hotel"));
			hotelListe.add(new Hotel("Hotel2", 2, "Bonn", "Sehr schönes Hotel"));
			hotelListe.add(new Hotel("Hotel3", 3, "Bonn", "Sehr schönes Hotel"));
			hotelListe.add(new Hotel("Hotel4", 4, "Bonn", "Sehr schönes Hotel"));
			hotelListe.add(new Hotel("Hotel5", 5, "Bonn", "Sehr schönes Hotel"));
			hotelListe.add(new Hotel("Hotel6", 6, "Bonn", "Sehr schönes Hotel"));
			hotelListe.add(new Hotel("Hotel7", 7, "Bonn", "Sehr schönes Hotel"));
			hotelListe.add(new Hotel("Hotel8", 8, "Bonn", "Sehr schönes Hotel"));

		}
		return hotelListe;
	}
}
