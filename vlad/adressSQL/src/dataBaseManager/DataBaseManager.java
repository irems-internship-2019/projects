package dataBaseManager;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DataBaseManager {


	private Connection connection = null;

	public Connection connectToDataBase() {

		try {
			FileInputStream fis = new FileInputStream("C:\\Users\\Eliza\\git\\projects\\vlad\\Adress\\src\\dataBaseManager\\connection.prop");

			Properties p = new Properties();
			   	p.load(fis);
			    String dname= (String) p.get ("Dname"); 
		        String url= (String) p.get ("URL"); 
		        String username= (String) p.get ("Uname"); 
		        String password= (String) p.get ("password"); 
		
		        Class.forName(dname);

			connection = DriverManager.getConnection(url, username, password);
			

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Opened database successfully");
		return connection;
	}

}
