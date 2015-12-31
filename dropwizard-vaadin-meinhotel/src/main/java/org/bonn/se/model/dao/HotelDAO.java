package org.bonn.se.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.bonn.se.model.objects.dto.Hotel;

public class HotelDAO extends AbstractDAO {
	private static HotelDAO dao = null;

	public static HotelDAO getInstance() {
		if (dao == null) {
			dao = new HotelDAO();
		}
		return dao;
	}

	private HotelDAO() {
	}

	public List<Hotel> getHotelByLocation(String ort) {
		Statement statement = getStatement();

		ResultSet resultSet = null;

		try {
			resultSet = statement.executeQuery("SELECT * FROM hotel WHERE ort = \'" + ort + "\'");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (resultSet == null) {
			return null;
		}

		List<Hotel> liste = new ArrayList<Hotel>();
		Hotel hotel = null;

		try {
			while (resultSet.next()) {
				hotel = new Hotel();
				hotel.setId(resultSet.getInt(1));
				hotel.setName(resultSet.getString(2));
				hotel.setOrt(resultSet.getString(3));
				hotel.setDescription(resultSet.getString(4));
				liste.add(hotel);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return liste;
	}
}
