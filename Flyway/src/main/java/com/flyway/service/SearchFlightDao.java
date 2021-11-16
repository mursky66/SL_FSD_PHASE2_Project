package com.flyway.service;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.flyway.models.Airline;
import com.flyway.models.Flight;
import com.flyway.utils.ConnectionUtil;

public class SearchFlightDao extends ConnectionUtil{
	
	public List<Flight> searchFlight(Long fromPlaceId, Long toPlaceId, Date date) {

		final List<Flight> flights = new ArrayList<>();
		ResultSet rs = null;

		final String sql = "SELECT al.airlineName, f.flightId, f.flightcode, f.airlineid, "
				+ "f.fromPlaceId, f.toPlaceId, f.passengerCapacity, "
				+ "f.departureDate "
				+ " FROM flights f "
				+ " INNER JOIN airlines al on al.airlineId = f.airlineid "
				+ " WHERE f.fromPlaceId = ? AND f.toPlaceId =?";
				//+ " AND f.departureDate =?";
				
		try (final PreparedStatement stmt = getCon().prepareStatement(sql);) {
			
			stmt.setLong(1, fromPlaceId);
			stmt.setLong(2, toPlaceId);
			//stmt.setDate(3, date);

			rs = stmt.executeQuery();

			while (rs.next()) {
				Flight flight = new Flight();
				flight.setAirlineName(rs.getString(1));
				flight.setFlightId(rs.getLong(2));
				flight.setFlightCode(rs.getString(3));
				flight.setAirlineId(rs.getLong(4));
				//flight.setFromePlaceId(rs.getLong(5));
//				
//				airline.setAirlineName(rs.getString("airlineName"));
//				airline.setAirlineCode(rs.getString("airlineCode"));
				flights.add(flight);
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
		return flights;
	}

}
