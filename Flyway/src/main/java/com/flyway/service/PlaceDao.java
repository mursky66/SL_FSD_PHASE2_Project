package com.flyway.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.flyway.models.Place;
import com.flyway.utils.ConnectionUtil;

public class PlaceDao extends ConnectionUtil {

	public boolean savePlace(final Place place) {

		final String sql = "INSERT INTO places(placeName, placeCode, latitude, longitude) "
				+ " VALUES(?, ?, ?, ?)";

		try (final PreparedStatement stmt = getCon().prepareStatement(sql);) {

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

	public List<Place> listPlaces() {

		final List<Place> places = new ArrayList<>();
		ResultSet rs = null;

		final String sql = "SELECT placeId, placeName, placeCode "
				+ " FROM places "
				+ " WHERE placeName IS NOT NULL"
				+ " ORDER BY placeName";

		try (final PreparedStatement stmt = getCon().prepareStatement(sql);) {

			rs = stmt.executeQuery();

			while (rs.next()) {
				Place place = new Place();
				place.setPlaceId(rs.getLong("placeId"));
				place.setPlaceName(rs.getString("placeName"));
				place.setPlaceCode(rs.getString("placeCode"));
				places.add(place);
			}

		} catch (Exception ex) {
			System.err.println(ex.getMessage());

		} finally {
			if (Objects.nonNull(rs)) {
				try {
					rs.close();
				} catch (SQLException ex) {
					System.err.println(ex.getMessage());
				}
			}
		}
		return places;
	}
}
