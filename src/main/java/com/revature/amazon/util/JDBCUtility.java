package com.revature.amazon.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.postgresql.Driver;

public class JDBCUtility {
	
	public static Connection getConnection() throws SQLException {
		String url = "jdbc:postgresql://amazon-db.cejzf6nzfwfc.us-east-2.rds.amazonaws.com:5432/postgres";
		String username = "postgres";
		String password = "password";
	
		Connection connection = null;
		
		DriverManager.registerDriver(new Driver());
		connection = DriverManager.getConnection(url, username, password);
		
		return connection;
		
	}
}
