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

import com.flyway.constants.FlywayConstant;
import com.flyway.models.User;
import com.flyway.service.UserDao;

@WebServlet("/ChangePasswordServlet")
public class ChangePasswordServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private final UserDao userDao = new UserDao();

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	
		final RequestDispatcher rd = request.getRequestDispatcher("./changePassword.jsp");
		rd.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		final String currentPwd = request.getParameter("currentPwd");
		final String newPwd = request.getParameter("newPwd");
		final String confirmPwd = request.getParameter("confirmPwd");

		final RequestDispatcher rd = request.getRequestDispatcher("./changePassword.jsp");
		if(Objects.isNull(newPwd) || Objects.isNull(confirmPwd) || Objects.isNull(currentPwd)) {
			request.setAttribute("MSG", "Password should not be null");
			request.setAttribute("MSGTYPE", "ERROR");
			rd.forward(request, response);
			return;
			
		}else {
			if(!newPwd.equals(confirmPwd)) {
				request.setAttribute("MSG", "New Password and Confirm password not matched");
				request.setAttribute("MSGTYPE", "ERROR");
				rd.forward(request, response);
				return;
			}
		}		
		
		HttpSession session = request.getSession(false);
		if(Objects.nonNull(session)) {
			if(Objects.nonNull(session.getAttribute(FlywayConstant.USER))) {
				final User user = (User)session.getAttribute(FlywayConstant.USER);
				boolean hasUpdate = userDao.changePassword(newPwd, user.getUserId());
				
				if (hasUpdate) {
					request.setAttribute("MSG", "Password changed successfully");
					request.setAttribute("MSGTYPE", "SUCCESS");
					rd.forward(request, response);
					return;
				}
			}
		}
		
		request.setAttribute("MSG", "Password not changed");
		request.setAttribute("MSGTYPE", "ERROR");
		rd.forward(request, response);
	}

}
