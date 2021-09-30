package com.flyway.web;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.flyway.models.User;
import com.flyway.service.UserDao;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final UserDao userDao = new UserDao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher rd = request.getRequestDispatcher("/userregister.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		final String email = request.getParameter("email");
		final String password = request.getParameter("password");
		final String firstName = request.getParameter("firstName");
		final String lastName = request.getParameter("lastName");
		final String age = request.getParameter("age");

		User user = new User();
		user.setEmail(email);
		user.setPassword(password);
		user.setFirstName(firstName);
		user.setLastName(lastName);

		if (Objects.nonNull(age) && !age.isEmpty()) {
			user.setAge(Integer.valueOf(age));
		}

		boolean isSaved = userDao.saveUser(user);

		if (isSaved) {
			request.setAttribute("MSG", "User registered successfully");
			request.setAttribute("MSGTYPE", "SUCCESS");
		} else {
			request.setAttribute("MSG", "User Registration failed");
			request.setAttribute("MSGTYPE", "ERROR");
		}
		RequestDispatcher rd = request.getRequestDispatcher("/userregister.jsp");
		rd.forward(request, response);

	}

}
