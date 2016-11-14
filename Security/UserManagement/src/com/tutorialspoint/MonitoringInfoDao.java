package com.tutorialspoint;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class MonitoringInfoDao {

	private Connection connect = null;

	public MonitoringInfo getStatusInfo() {

		MonitoringInfo status = new MonitoringInfo();

		status.setCoreAvailable(Runtime.getRuntime().availableProcessors());

		long freeMemory = Runtime.getRuntime().freeMemory();
		long totalMemory = Runtime.getRuntime().totalMemory();
		long memory = freeMemory + totalMemory;
		int memoryPorcentage = (int) (totalMemory * 100 / memory);

		status.setMemoryAvailable(memoryPorcentage);

		status.setDBStatus(checkDB());

		return status;
	}

	private String checkDB() {

		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");

			// Setup the connection with the DB
			connect = DriverManager.getConnection(
					"jdbc:mysql://localhost/service_desk?" + "useSSL=true&user=sqluser&password=sqluserpw");

			connect.close();

			return "Ok";

		} catch (SQLException e) {
			return "Down";
		} catch (ClassNotFoundException e) {
			return "Unknown";
		} finally {
			if (connect != null)
				try {
					connect.close();
				} catch (SQLException ignore) {
				}
		}

	}

}
