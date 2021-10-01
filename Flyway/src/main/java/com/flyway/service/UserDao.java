package com.flyway.service;

import java.sql.PreparedStatement;

import com.flyway.models.User;
import com.flyway.utils.ConnectionUtil;

public class UserDao extends ConnectionUtil {

	public boolean saveUser(final User user) {

		final String sql = "INSERT INTO users(firstName, lastName, password, email, age) "
				+ " VALUES(?, ?, ?, ?, ?)";

		try (PreparedStatement stmt = getCon().prepareStatement(sql);) {

			stmt.setString(1, user.getFirstName());
			stmt.setString(2, user.getLastName());
			stmt.setString(3, user.getPassword());
			stmt.setString(4, user.getEmail());
			stmt.setInt(5, user.getAge());

			int insertCnt = stmt.executeUpdate();
			if (insertCnt > 0) {
				return true;
			}

		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
		return false;
	}
}
