package dataBaseManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.management.Query;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import de.vogella.jpa.simple.model.Todo;
import model.Address;
import model.Contact;

public class DataBaseServices {

	DataBaseManager manager = new DataBaseManager();
	 private static final String PERSISTENCE_UNIT_NAME = "todos";
	    private static EntityManagerFactory factory;
	
	public void persistenceLoad()
	{
		
		 factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	        EntityManager em = factory.createEntityManager();
	        // read the existing entries and write to console
	        Query q = (Query) em.createQuery("select t from Contacsadst t");
	        List<Todo> todoList = ((javax.persistence.Query) q).getResultList();
	        for (Todo todo : todoList) {
	            System.out.println(todo);
	        }
	        System.out.println("Size: " + todoList.size());
	        
	} 
	
	public void persistenceInsert(Contact contact)
	{
		   factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	        EntityManager em = factory.createEntityManager();
	        em.getTransaction().begin();
	        Contact todo = new Contact();
	        todo.setFirstName(contact.getFirstName());
	        todo.setLastName(contact.getLastName());
	        em.persist(todo);
	        em.getTransaction().commit();

	        em.close();
	        
	}
	
	
	public void insertContact(Contact contact) {
		Statement stmt = null;
		try {
			Connection conn = manager.connectToDataBase();
			System.out.println("Opened database successfully");
			stmt = conn.createStatement();

			String insertStatment = "with insert_query as(\r\n"
					+ "	INSERT INTO public.adress(country,city,street,postalcode)\r\n" + "	 VALUES ('"
					+ contact.getAddress().getCountry() + "','" + contact.getAddress().getCity() + "','"
					+ contact.getAddress().getStreet() + "','" + contact.getAddress().getPostalCode() + "')\r\n"
					+ "	 returning adress_id \r\n" + ")\r\n" + "\r\n" + "insert into public.contacts \r\n"
					+ "(firstname, lastname, phonenumber, email, addr_id)\r\n" + "VALUES('" + contact.getFirstName()
					+ "','" + contact.getLastName() + "','" + contact.getphoneNumber() + "','"
					+ contact.getEmailAdress() + "',(select adress_id from insert_query))";

			stmt.executeUpdate(insertStatment);

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);

		}
		System.out.println("Operation done successfully");

	}
	
	
	public void JPA()
	{
		  factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	        EntityManager em = factory.createEntityManager();
	        
	}
	

	public ArrayList<Contact> loadContacts() {
		Statement newStatment;
		ArrayList<Contact> contacts = new ArrayList<Contact>();
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

				contacts.add(contact);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Operation done successfully");
		return contacts;
	}

	public void updateContact(Contact contact) {
		Statement stmt = null;

		try {
			Connection conn = manager.connectToDataBase();
			stmt = conn.createStatement();

			String updateSql = "with update_query as(\r\n" + "UPDATE public.contacts \r\n" + "	SET \r\n"
					+ "		firstname='" + contact.getFirstName() + "',lastname='" + contact.getLastName()
					+ "',phonenumber='" + contact.getphoneNumber() + "',email='" + contact.getEmailAdress() + "'\r\n"
					+ "\r\n" + "WHERE  contact_id=" + contact.getIdInt() + "\r\n" + "	returning addr_id\r\n" + ")\r\n"
					+ "				UPDATE public.adress \r\n" + "		SET \r\n" + "		country='"
					+ contact.getAddress().getCountry() + "',city='" + contact.getAddress().getCity() + "',street='"
					+ contact.getAddress().getStreet() + "',postalcode='" + contact.getAddress().getPostalCode()
					+ "'\r\n" + "	        WHERE adress_id  in (SELECT addr_id FROM update_query )";

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
			String deleteQuery = "with delete_query as(\r\n" + "	DELETE FROM public.contacts\r\n"
					+ "	WHERE contact_id=" + id + "\r\n" + "	 returning addr_id\r\n" + ")\r\n" + "\r\n"
					+ "DELETE FROM public.adress \r\n" + "WHERE (adress_id) IN (SELECT addr_id FROM delete_query)";

			stmt.executeUpdate(deleteQuery);

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Operation done successfully");
	}

}
