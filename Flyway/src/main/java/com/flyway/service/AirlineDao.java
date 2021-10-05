package com.flyway.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.flyway.models.Airline;
import com.flyway.utils.ConnectionUtil;

public class AirlineDao extends ConnectionUtil {

	public boolean saveAirline(final Airline airline) {

		final String sql = "INSERT INTO airlines(airlineName, airlineCode) "
				+ " VALUES(?, ?)";

		try (PreparedStatement stmt = getCon().prepareStatement(sql);) {

			stmt.setString(1, airline.getAirlineName());
			stmt.setString(2, airline.getAirlineCode());

			int insertCnt = stmt.executeUpdate();
			if (insertCnt > 0) {
				return true;
			}

		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
		return false;
	}

	public List<Airline> listAirlines() {

		final List<Airline> airlines = new ArrayList<>();
		ResultSet rs = null;

		final String sql = "SELECT airlineId, airlineName, airlineCode "
				+ " FROM airlines " + " WHERE airlineName IS NOT NULL"
				+ " ORDER BY airlineName";

		try (final PreparedStatement stmt = getCon().prepareStatement(sql);) {

			rs = stmt.executeQuery();

			while (rs.next()) {
				Airline airline = new Airline();
				airline.setAirlineId(rs.getLong("airlineId"));
				airline.setAirlineName(rs.getString("airlineName"));
				airline.setAirlineCode(rs.getString("airlineCode"));
				airlines.add(airline);
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
		return airlines;
	}
}
