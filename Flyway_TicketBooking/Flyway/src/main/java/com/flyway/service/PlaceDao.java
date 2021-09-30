package com.flyway.service;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.flyway.models.Place;
import com.flyway.utils.ConnectionUtil;

public class PlaceDao extends ConnectionUtil {

	public boolean savePlace(final Place place) {
		try {

			final String sql = "INSERT INTO places(placeName, placeCode, latitude, longitude) "
					+ " VALUES(?, ?, ?, ?)";

			Connection con = getCon();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, place.getPlaceName());
			stmt.setString(2, place.getPlaceCode());
			stmt.setDouble(3, place.getLatitude());
			stmt.setDouble(4, place.getLongitude());

			int insertCnt = stmt.executeUpdate();
			if (insertCnt > 0) {
				return true;
			}

		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
		return false;
	}
}
