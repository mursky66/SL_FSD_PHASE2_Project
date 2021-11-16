package com.flyway.web;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.flyway.models.Flight;
import com.flyway.service.SearchFlightDao;

@WebServlet("/SearchFlightServlet")
public class SearchFlightServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	final SearchFlightDao dao = new SearchFlightDao();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doPost CALLED");

		String fromPlaceId = request.getParameter("fromPlaceId");
		String toPlaceId = request.getParameter("toPlaceId");
		String departureDate = request.getParameter("departureDate");
		
		

		Date dt = new Date(0L);

		// List<Flight> flights = dao.searchFlight(Long.valueOf(fromPlaceId), Long.valueOf(toPlaceId), dt);
		List<Flight> flights = dao.searchFlight(3L,6L,dt);
		request.setAttribute("FLIGHTS", flights);
		
		System.out.println("flights" +flights);

		RequestDispatcher rd = request.getRequestDispatcher("./index.jsp");
		rd.forward(request, response);
	}

}
