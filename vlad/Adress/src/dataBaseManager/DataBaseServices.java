package dataBaseManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Address;
import model.Contact;
import model.ContactProvider;

public class DataBaseServices {

	DataBaseManager manager = new DataBaseManager();

	public void insertContact(int id,String firstName, String lastName, String address, String phoneNum, String eMail,
			String country, String city, String postalCode) {
		Statement stmt = null;
		try {
			Connection conn = manager.connectToDataBase();
			System.out.println("Opened database successfully");
			stmt = conn.createStatement();

			String insertStatment = "with insert_query as(\r\n"
					+ "	INSERT INTO public.adress(country,city,street,postalcode)\r\n" + "	 VALUES ('" + country
					+ "','" + country + "','" + city + "','" + address + "')\r\n" + "	 returning adress_id \r\n"
					+ ")\r\n" + "\r\n" + "insert into public.contacts \r\n"
					+ "(firstname, lastname, phonenumber, email, addr_id)\r\n" + "VALUES('" + firstName + "','"
					+ lastName + "','" + phoneNum + "','" + eMail + "',(select adress_id from insert_query))";

			stmt.executeUpdate(insertStatment);
	
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Records created successfully");
	}

	public void showContacts() {
		Statement newStatment;

		try {

			Connection conn = manager.connectToDataBase();

			newStatment = conn.createStatement();

			ResultSet result = newStatment.executeQuery("SELECT * \r\n" + "FROM public.contacts\r\n"
					+ "INNER JOIN public.adress\r\n" + "ON addr_id=adress_id;");

			while (result.next()) {

				Address address = new Address(result.getString("country"), result.getString("city"),
						result.getString("street"), result.getString("postalcode"));

				Contact contact = new Contact(result.getString("firstname"), address, result.getString("lastname"),
						result.getString("phonenumber"), result.getString("email"));

				contact.setId(result.getInt("contact_id"));

				ContactProvider.INSTANCE.addContacts(contact);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Operation done successfully");
	}

	public void updateContact(int id, String firstName, String lastName, String phoneNum, String eMail, String country,
			String city,String street, String postalCode) {
		Statement stmt = null;

		try {
			Connection conn = manager.connectToDataBase();
			stmt = conn.createStatement();
		

			String updateSql = "with update_query as(\r\n" + "UPDATE public.contacts \r\n" + "	SET \r\n"
					+ "		firstname='" + firstName + "',lastname='" + lastName + "',phonenumber='" + phoneNum
					+ "',email='" + eMail + "'\r\n" + "\r\n" + "WHERE  contact_id="+id+"\r\n" + "	returning addr_id\r\n"
					+ ")\r\n" + "				UPDATE public.adress \r\n" + "		SET \r\n"
					+ "		country='"+country+"',city='"+city+"',street='"+street+"',postalcode='"+postalCode+"'\r\n"
					+ "	        WHERE adress_id  in (SELECT addr_id FROM update_query )";
			
			stmt.executeUpdate(updateSql);
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Operation done successfully");
	}

	public void deleteContact(int id) {
		Statement stmt = null;

		try {
			Connection conn = manager.connectToDataBase();
			stmt = conn.createStatement();
			String deleteQuery = "with delete_query as(\r\n" + 
					"	DELETE FROM public.contacts\r\n" + 
					"	WHERE contact_id="+id+"\r\n" + 
					"	 returning addr_id\r\n" + 
					")\r\n" + 
					"\r\n" + 
					"DELETE FROM public.adress \r\n" + 
					"WHERE (adress_id) IN (SELECT addr_id FROM delete_query)";
			
			stmt.executeUpdate(deleteQuery);
		

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Operation done successfully");
	}

}
