package com.flyway.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.flyway.models.User;
import com.flyway.utils.ConnectionUtil;

public class LoginDao extends ConnectionUtil {

	public User doLogin(final String email, final String pwd) {
		try {
			User user = null;

			final String sql = "SELECT userId, firstName, lastName, email, age " + " FROM users"
					+ " WHERE email=? AND password=?";

			Connection con = getCon();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, email);
			stmt.setString(2, pwd);

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {

				Long dbUserId = rs.getLong("userId");
				String dbFirstName = rs.getString("firstName");
				String dbLastName = rs.getString("lastName");
				String dbEmail = rs.getString("email");
				int dbAge = rs.getInt("age");

				user = new User();
				user.setUserId(dbUserId);
				user.setEmail(dbEmail);
				user.setFirstName(dbFirstName);
				user.setLastName(dbLastName);
				user.setAge(dbAge);
			}

			return user;
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
			return null;
		}
	}
}
