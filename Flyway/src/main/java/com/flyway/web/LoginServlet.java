package com.flyway.web;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.flyway.models.User;
import com.flyway.service.LoginDao;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	final LoginDao loginDao = new LoginDao();

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
		rd.forward(request, response);

	}

	@Override
	protected void doPost(final HttpServletRequest request,
			final HttpServletResponse response)
			throws ServletException, IOException {

		final String email = request.getParameter("email");
		final String password = request.getParameter("password");

		User user = loginDao.doLogin(email, password);

		if (Objects.isNull(user)) {
			request.setAttribute("MSG", "Login Falied, Invalid credentials");
			RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
			rd.forward(request, response);
		} else {
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("USER", user);

			RequestDispatcher rd = request.getRequestDispatcher("/admin.jsp");
			rd.forward(request, response);
		}

	}

}
