package database;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.DataBaseManager;
import model.Address;
import model.Contact;
import model.ContactsTable;

/**
 * Session Bean implementation class LoadContacts
 */
@Stateless
@LocalBean
public class Services implements ServicesRemote {

    /**
     * Default constructor. 
     */
    public Services() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public ArrayList<Contact> loadContatcs() {
		ArrayList<Contact> contacts = new ArrayList<Contact>();
		DataBaseManager manager = new DataBaseManager();
		EntityManager entityManager = manager.enableConnection();
		Query q = entityManager.createQuery("select t from ContactsTable t");
		@SuppressWarnings("unchecked")
		List<ContactsTable> todoList = q.getResultList();
		for (ContactsTable todo : todoList) {
			Contact contact = new Contact(todo.getContactid(), todo.getFirstName(),
					new Address(todo.getAddress().getCountry(), todo.getAddress().getCity(),
							todo.getAddress().getStreet(), todo.getAddress().getPostalCode()),
					todo.getLastName(), todo.getPhoneNumber(), todo.getEmailAddress());
			contacts.add(contact);
	}
		return contacts;

	}
}
