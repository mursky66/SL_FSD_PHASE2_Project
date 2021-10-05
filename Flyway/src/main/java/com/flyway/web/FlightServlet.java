package com.flyway.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.flyway.constants.FlywayConstant;
import com.flyway.models.Flight;
import com.flyway.service.AirlineDao;
import com.flyway.service.FlightDao;
import com.flyway.service.PlaceDao;

@WebServlet("/FlightServlet")
public class FlightServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final AirlineDao airlineDao = new AirlineDao();
	private final PlaceDao placeDao = new PlaceDao();
	private final FlightDao flightDao = new FlightDao();
	private final SimpleDateFormat fmt = new SimpleDateFormat(
			FlywayConstant.DATE_FORMAT);

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// set Airlines for drop-down
		request.setAttribute(FlywayConstant.AIRLINES,
				airlineDao.listAirlines());
		request.setAttribute(FlywayConstant.PLACES, placeDao.listPlaces());

		RequestDispatcher rd = request.getRequestDispatcher("./flight.jsp");
		rd.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {
			final String airlineId = request.getParameter("airlineId");
			final String flightCode = request.getParameter("flightCode");
			final String fromPlaceId = request.getParameter("fromPlaceId");
			final String toPlaceId = request.getParameter("toPlaceId");
			final String passengerCapacity = request
					.getParameter("passengerCapacity");
			final String departureDate = request.getParameter("departureDate");

			final Flight flight = new Flight();
			flight.setFlightCode(flightCode);

			flight.setToPlaceId(null);
			flight.setPassengerCapacity(null);
			flight.setDepartureDate(null);

			if (Objects.nonNull(airlineId) && !airlineId.isEmpty()) {
				flight.setAirlineId(Long.valueOf(airlineId));
			}
			if (Objects.nonNull(fromPlaceId) && !fromPlaceId.isEmpty()) {
				flight.setFromPlaceId(Long.valueOf(fromPlaceId));
			}
			if (Objects.nonNull(toPlaceId) && !toPlaceId.isEmpty()) {
				flight.setToPlaceId(Long.valueOf(toPlaceId));
			}
			if (Objects.nonNull(passengerCapacity)
					&& !passengerCapacity.isEmpty()) {
				flight.setPassengerCapacity(Integer.valueOf(passengerCapacity));
			}

			if (Objects.nonNull(departureDate) && !departureDate.isEmpty()) {
				Date date = fmt.parse(departureDate);
				flight.setDepartureDate(
						new java.sql.Date(date.toInstant().toEpochMilli()));
			}

			boolean isSaved = flightDao.saveFlight(flight);

			if (isSaved) {
				request.setAttribute("MSG", "Place added successfully");
				request.setAttribute("MSGTYPE", "SUCCESS");
			} else {
				request.setAttribute("MSG", "Place adding failed");
				request.setAttribute("MSGTYPE", "ERROR");
			}
			RequestDispatcher rd = request.getRequestDispatcher("./flight.jsp");
			rd.forward(request, response);

		} catch (final Exception ex) {
			System.err.print(ex.getMessage());
		}
	}

}
