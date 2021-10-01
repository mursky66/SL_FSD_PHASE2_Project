package com.flyway.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionUtil {

	protected final Connection getCon() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/flyway?createDatabaseIfNotExist=true&serverTimezone=UTC", "root", "root");
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
			return null;
		}
	}
}
