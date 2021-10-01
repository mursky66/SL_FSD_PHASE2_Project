package com.flyway.service;

import java.sql.PreparedStatement;

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
}
