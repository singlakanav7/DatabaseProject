package com.javainuse.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleJdbcConnection {
	private static String url = "jdbc:oracle:thin:@artemis.vsnet.gmu.edu:1521/vse18c.vsnet.gmu.edu";
	private static String uname = "ksingla2";
	private static String pass = "adikraja";

	public static Connection getDatabaseConnection() {
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, uname, pass);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (con == null) {
			System.out.println("Connection Unsuccesfull");
		} else {
			System.out.println("Connection Succesfull");
		}

		return con;
	}

}
