package com.flyway.service;

import java.sql.PreparedStatement;

import com.flyway.models.Flight;
import com.flyway.utils.ConnectionUtil;

public class FlightDao extends ConnectionUtil {

	public boolean saveFlight(final Flight flight) {

		final String sql = "INSERT INTO flights (flightCode, airlineId, fromPlaceId, toPlaceId, passengerCapacity,departureDate) "
				+ " VALUES(?, ?, ?, ?, ?, ?)";

		try (PreparedStatement stmt = getCon().prepareStatement(sql);) {

			stmt.setString(1, flight.getFlightCode());
			stmt.setLong(2, flight.getAirlineId());
			stmt.setLong(3, flight.getFromPlaceId());
			stmt.setLong(4, flight.getToPlaceId());
			stmt.setInt(5, flight.getPassengerCapacity());
			stmt.setDate(6, flight.getDepartureDate());

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
