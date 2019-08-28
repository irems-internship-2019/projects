package dataBaseManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.persistence.EntityManager;

import javax.persistence.Query;

import com.mylogic.DataBaseService;
import com.mylogic.DataBaseServiceRemote;

import model.Address;
import model.AddressTable;
import model.Contact;
import model.ContactsTable;

public class DataBaseServices {

	DataBaseManager manager = new DataBaseManager();

	public void insertContact(Contact contact) {
		try {

			EntityManager entityManager = manager.enableConnection();

			entityManager.getTransaction().begin();
			ContactsTable contactTable = new ContactsTable();
			AddressTable addressTable = new AddressTable();

			addressTable.setCountry(contact.getAddress().getCountry());
			addressTable.setCity(contact.getAddress().getCity());
			addressTable.setStreet(contact.getAddress().getStreet());
			addressTable.setPostalCode(contact.getAddress().getPostalCode());

			contactTable.setFirstName(contact.getFirstName());
			contactTable.setLastName(contact.getLastName());
			contactTable.setPhoneNumber(contact.getphoneNumber());
			contactTable.setEmailAddress(contact.getEmailAdress());
			contactTable.setAddress(addressTable);

			entityManager.persist(contactTable);
			entityManager.persist(addressTable);

			entityManager.getTransaction().commit();

			entityManager.close();

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Operation done successfully");

	}

	public ArrayList<Contact> loadContacts() {
		return null;

//		ArrayList<Contact> contacts = new ArrayList<Contact>();
//		try {
//
//			EntityManager entityManager = manager.enableConnection();
//			Query q = entityManager.createQuery("select t from ContactsTable t");
//			@SuppressWarnings("unchecked")
//			List<ContactsTable> todoList = q.getResultList();
//			for (ContactsTable todo : todoList) {
//				Contact contact = new Contact(todo.getContactid(), todo.getFirstName(),
//						new Address(todo.getAddress().getCountry(), todo.getAddress().getCity(),
//								todo.getAddress().getStreet(), todo.getAddress().getPostalCode()),
//						todo.getLastName(), todo.getPhoneNumber(), todo.getEmailAddress());
//				contacts.add(contact);
//
//			}
//
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//ArrayList<Contact> contacts = null ;
//		try{   
//			Properties props = new Properties();
//	        props.put("java.naming.factory.url.pkgs","org.jboss.ejb.client.naming");
//	        InitialContext context = new InitialContext(props);
//	        String appName = "";        	 
//	        String moduleName = "AddressBookEJB";
//	        String distinctName = "";        	 
//	        String beanName = DataBaseService.class.getSimpleName();   
//	        String interfaceName = DataBaseServiceRemote.class.getName();
//	        String name = "ejb:" + appName + "/" + moduleName + "/" +  distinctName    + "/" + beanName + "!" + interfaceName;
// 	       System.out.println(name);
// 	      DataBaseServiceRemote bean = (DataBaseServiceRemote) context.lookup(name);
// 	 contacts =bean.loadContacts();
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		return contacts;
//
	}

	public void updateContact(Contact contact) {

		try {

			EntityManager entityManager = manager.enableConnection();

			ContactsTable contactTable = entityManager.find(ContactsTable.class, contact.getIdInt());
			System.out.print("!=" + contact.getIdInt());
			entityManager.getTransaction().begin();

			contactTable.setFirstName(contact.getFirstName());
			contactTable.setLastName(contact.getLastName());
			contactTable.setPhoneNumber(contact.getphoneNumber());
			contactTable.setEmailAddress(contact.getEmailAdress());

			contactTable.getAddress().setCountry(contact.getAddress().getCountry());
			contactTable.getAddress().setCity(contact.getAddress().getCity());
			contactTable.getAddress().setStreet(contact.getAddress().getStreet());
			contactTable.getAddress().setPostalCode(contact.getAddress().getPostalCode());

			entityManager.getTransaction().commit();
			entityManager.close();

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Operation done successfully");
	}

	public void deleteContact(Long id) {
		try {

			EntityManager entityManager = manager.enableConnection();
			ContactsTable contact = entityManager.find(ContactsTable.class, id);

			entityManager.getTransaction().begin();
			entityManager.remove(contact.getAddress());
			entityManager.remove(contact);
			entityManager.getTransaction().commit();

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Operation done successfully");
	}

}
