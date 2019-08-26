package dataBaseManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.Address;
import model.Contact;
import model.ContactProvider;
import model.ContactsTable;
import ui.messagedialogs.ExceptionDialogs;

public class DataBaseServices {

	DataBaseManager manager = new DataBaseManager();
	  private static final String PERSISTENCE_UNIT_NAME = "contacts";
	    private static EntityManagerFactory factory;
	    
	    

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
		System.out.println("Operation done successfully");

		
	}

	public ArrayList<Contact> loadContacts() {
		
		ArrayList<Contact> contacts = new ArrayList<Contact>();
		try {

			factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	        EntityManager em = factory.createEntityManager();
	        // read the existing entries and write to console
//	        Query q = em.createQuery("select t from Todo t");
//	        List<ContactsTable> todoList = q.getResultList();
//	        for (ContactsTable todo : todoList) {
//	        }

	        em.getTransaction().begin();
	        ContactsTable todo = new ContactsTable();
	        todo.setFirstName("This is a test");
	        todo.setLastName("This is a test");
	        todo.setPhoneNumber("This is a test");
	        todo.setEmailAddress("This is a test");
	        
	        em.persist(todo);
	        em.getTransaction().commit();

	        em.close();
			
		

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Operation done successfully");
		return contacts;
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
