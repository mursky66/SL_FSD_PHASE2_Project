package com.flyway.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flyway.models.Place;
import com.flyway.service.PlaceDao;

@WebServlet("/ListPlacesServlet")
public class ListPlacesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final PlaceDao placeDao = new PlaceDao();

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		final List<Place> places = placeDao.listPlaces();

		final ObjectMapper mapper = new ObjectMapper();

		final PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.print(mapper.writeValueAsString(places));
		out.flush();
	}

}
