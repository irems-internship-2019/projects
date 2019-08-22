package dataBaseManager;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataBaseManager {
	String url = "jdbc:postgresql://localhost:5432/postgres";
	String userName = "postgres";
	String passWord = "vlad";
	private Connection connection = null;

	public Connection connectToDataBase() {

		try {

			connection = DriverManager.getConnection(url, userName, passWord);

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Opened database successfully");
		return connection;
	}

}
