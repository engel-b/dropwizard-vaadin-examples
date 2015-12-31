package org.bonn.se.process.control;

import java.util.List;

import org.bonn.se.model.dao.HotelDAO;
import org.bonn.se.model.objects.dto.Hotel;

public class HotelSearch {
	public static HotelSearch hotelSearch = null;

	public static HotelSearch getHotelSearch() {
		if (hotelSearch == null) {
			hotelSearch = new HotelSearch();
		}
		return hotelSearch;
	}

	private HotelSearch() {
	}

	public List<Hotel> getHotelByOrt(String ort) {
		return HotelDAO.getInstance().getHotelByLocation(ort);
	}
}
