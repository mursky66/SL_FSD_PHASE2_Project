package com.flyway.web;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.flyway.models.Place;
import com.flyway.service.PlaceDao;

@WebServlet("/PlaceServlet")
public class PlaceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final PlaceDao placeDao = new PlaceDao();

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("./place.jsp");
		rd.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		final String placeName = request.getParameter("placeName");
		final String placeCode = request.getParameter("placeCode");
		final String latitude = request.getParameter("latitude");
		final String longitude = request.getParameter("longitude");

		Place place = new Place();
		place.setPlaceName(placeName);
		place.setPlaceCode(placeCode);

		if (Objects.nonNull(latitude) && !latitude.isEmpty()) {
			place.setLatitude(Double.valueOf(latitude));
		}
		if (Objects.nonNull(longitude) && !longitude.isEmpty()) {
			place.setLongitude(Double.valueOf(longitude));
		}

		boolean isSaved = placeDao.savePlace(place);

		if (isSaved) {
			request.setAttribute("MSG", "Place added successfully");
			request.setAttribute("MSGTYPE", "SUCCESS");
		} else {
			request.setAttribute("MSG", "Place adding failed");
			request.setAttribute("MSGTYPE", "ERROR");
		}
		RequestDispatcher rd = request.getRequestDispatcher("/place.jsp");
		rd.forward(request, response);
	}

}
