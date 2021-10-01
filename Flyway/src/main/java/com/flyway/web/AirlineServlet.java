package com.flyway.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.flyway.models.Airline;
import com.flyway.service.AirlineDao;

@WebServlet("/AirlineServlet")
public class AirlineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final AirlineDao airlineDao = new AirlineDao();

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("./airline.jsp");
		rd.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		final String airlineName = request.getParameter("airlineName");
		final String airlineCode = request.getParameter("airlineCode");

		final Airline airline = new Airline();
		airline.setAirlineName(airlineName);
		airline.setAirlineCode(airlineCode);

		boolean isSaved = airlineDao.saveAirline(airline);

		if (isSaved) {
			request.setAttribute("MSG", "Airline added successfully");
			request.setAttribute("MSGTYPE", "SUCCESS");
		} else {
			request.setAttribute("MSG", "Airline adding failed");
			request.setAttribute("MSGTYPE", "ERROR");
		}
		RequestDispatcher rd = request.getRequestDispatcher("/airline.jsp");
		rd.forward(request, response);
	}

}
