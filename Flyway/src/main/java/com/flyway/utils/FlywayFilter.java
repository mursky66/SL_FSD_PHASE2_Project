package com.flyway.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD,
		DispatcherType.INCLUDE, DispatcherType.ERROR}, urlPatterns = {"/*"})
public class FlywayFilter implements Filter {

	private static final List<String> PRIVATE_URLS = new ArrayList<>();
	static {
		PRIVATE_URLS.add("/Flyway/AirlineServlet");
		PRIVATE_URLS.add("/Flyway/FlightServlet");
		PRIVATE_URLS.add("/Flyway/PlaceServlet");
		PRIVATE_URLS.add("/Flyway/AdminServlet");
		PRIVATE_URLS.add("/ChangePasswordServlet");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		final HttpServletRequest httpReq = (HttpServletRequest) request;
		final HttpServletResponse httpRes = (HttpServletResponse) response;

		final String requestURL = httpReq.getRequestURI();

		if (PRIVATE_URLS.contains(requestURL)) {
			final HttpSession session = httpReq.getSession();
			if (Objects.isNull(session.getAttribute("USER"))) {
				httpRes.sendRedirect("./accessdenied.jsp");
				return;
			}
		}
		chain.doFilter(httpReq, httpRes);

	}

}
