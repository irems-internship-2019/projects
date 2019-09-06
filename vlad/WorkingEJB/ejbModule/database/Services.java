package database;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.DataBaseManager;
import model.AddressTable;
import model.ContactsTable;

/**
 * Session Bean implementation class LoadContacts
 */
@Stateless
@LocalBean
public class Services implements ServicesRemote {
	DataBaseManager manager = new DataBaseManager();
    /**
     * Default constructor. 
     */
    public Services() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public ArrayList<ContactsTable> loadContatcs() {
		ArrayList<ContactsTable> contacts = new ArrayList<ContactsTable>();
		EntityManager entityManager = manager.enableConnection();
		Query q = entityManager.createQuery("select t from ContactsTable t");
		@SuppressWarnings("unchecked")
		List<ContactsTable> todoList = q.getResultList();
		for (ContactsTable todo : todoList) {
			ContactsTable contact = new ContactsTable(todo.getContactid(), todo.getFirstName(),
					new AddressTable(todo.getAddress().getCountry(), todo.getAddress().getCity(),
							todo.getAddress().getStreet(), todo.getAddress().getPostalCode()),
					todo.getLastName(), todo.getPhoneNumber(), todo.getEmailAddress());
			
			contacts.add(contact);
	}
		return contacts;

	}

	@Override
	public void insertContact(ContactsTable contact) {
		
			try {
			EntityManager entityManager = manager.enableConnection();
	
			entityManager.getTransaction().begin();
			ContactsTable contactTable = new ContactsTable();
			AddressTable addressTable = new AddressTable();
			
			
		addressTable.setCountry(contact.getAddress().getCountry());
			addressTable.setCity(contact.getAddress().getCity());
			addressTable.setStreet(contact.getAddress().getStreet());
			addressTable.setPostalCode(contact.getAddress().getPostalCode());
			
			entityManager.persist(addressTable);

	
			contactTable.setFirstName(contact.getFirstName());
				contactTable.setLastName(contact.getLastName());
			contactTable.setPhoneNumber(contact.getPhoneNumber());
			contactTable.setEmailAddress(contact.getEmailAddress());
			contactTable.setAddress(addressTable);

			entityManager.persist(contactTable);
			
			entityManager.getTransaction().commit();

		entityManager.close();
	
			} catch (Exception e) {
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
				System.exit(0);
			}
			System.out.println("Operation done successfully");
	}

	@Override
	public void updateContacts(ContactsTable contact) {
		try {
				EntityManager entityManager = manager.enableConnection();
			
					ContactsTable contactTable = entityManager.find(ContactsTable.class, contact.getContactid());
			entityManager.getTransaction().begin();
		System.out.println("id="+contact.getContactid());
					contactTable.setFirstName(contact.getFirstName());
					contactTable.setLastName(contact.getLastName());
					contactTable.setPhoneNumber(contact.getPhoneNumber());
				contactTable.setEmailAddress(contact.getEmailAddress());
			
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
		
	

	@Override
	public void deleteContact(Long id) {
			try {

				EntityManager entityManager = manager.enableConnection();
				ContactsTable contact = entityManager.find(ContactsTable.class, id);

				entityManager.getTransaction().begin();
				entityManager.remove(contact);
				entityManager.remove(contact.getAddress());
				entityManager.getTransaction().commit();

			} catch (Exception e) {
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
				System.exit(0);
			}
			System.out.println("Operation done successfully");
		}
		
	}

