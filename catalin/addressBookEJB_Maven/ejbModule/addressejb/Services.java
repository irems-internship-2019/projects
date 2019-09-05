package addressejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import models.persons.Address;
import models.persons.Contact;
import services.server.AddressTable;
import services.server.ContactTable;
import services.server.ServerManager;

/**
 * Session Bean implementation class Servicess
 */
@Stateless
@LocalBean
public class Services implements ServicesRemote {
	 private ServerManager manager = new ServerManager();

	 @Override
	 public ArrayList<Contact> getServerContacts()
	    {
		ArrayList<Contact> contactElements = new ArrayList<Contact>();
		try
		{
		    EntityManager entityManger = manager.setConnection();

		    Query q = entityManger.createQuery("SELECT c FROM ContactTable c");
		    List<ContactTable> contacts = q.getResultList();
		    for (ContactTable contactElement : contacts)
		    {
			Contact contact = new Contact(contactElement.getContactid(), contactElement.getFirstName(),
				contactElement.getLastName(),
				new Address(contactElement.getAddress().getCountry(), contactElement.getAddress().getCity(),
					contactElement.getAddress().getStreet(), contactElement.getAddress().getPostalCode()),
				contactElement.getPhoneNumber(), contactElement.getEmailAddress());
			contactElements.add(contact);
		    }
		} catch (Exception e)
		{
		}
		return contactElements;
	    }
	 
	 @Override
	 public void addServerContact(Contact contact)
	    {
		EntityManager entityManger = manager.setConnection();

		entityManger.getTransaction().begin();
		ContactTable contactElement = new ContactTable();
		AddressTable addressElement = new AddressTable();

		addressElement.setCountry(contact.getAddress().getCountry());
		addressElement.setCity(contact.getAddress().getCity());
		addressElement.setStreet(contact.getAddress().getStreet());
		addressElement.setPostalCode(contact.getAddress().getPostal_code());
		entityManger.persist(addressElement);

		contactElement.setFirstname(contact.getFirstName());
		contactElement.setLastName(contact.getLastName());
		contactElement.setPhoneNumber(contact.getPhoneNumber());
		contactElement.setEmailAddress(contact.getEmailAddress());
		contactElement.setAddress(addressElement);
		entityManger.persist(contactElement);
		entityManger.getTransaction().commit();

		entityManger.close();
	    }
	 
	 @Override
	 public void editServerContact(Contact contact)
	    {
		EntityManager entityManger = manager.setConnection();

		ContactTable contactElement = entityManger.find(ContactTable.class, contact.getId());

		entityManger.getTransaction().begin();

		contactElement.setFirstname(contact.getFirstName());
		contactElement.setLastName(contact.getLastName());
		contactElement.setPhoneNumber(contact.getPhoneNumber());
		contactElement.setEmailAddress(contact.getEmailAddress());
		contactElement.getAddress().setCountry(contact.getAddress().getCountry());
		contactElement.getAddress().setCity(contact.getAddress().getCity());
		contactElement.getAddress().setStreet(contact.getAddress().getStreet());
		contactElement.getAddress().setPostalCode(contact.getAddress().getPostal_code());

		entityManger.getTransaction().commit();
	    }
	 
	 @Override
	 public void deleteServerContact(Contact contact)
	    {
		EntityManager entityManger = manager.setConnection();

		ContactTable contactElement = entityManger.find(ContactTable.class, contact.getId());

		entityManger.getTransaction().begin();
		entityManger.remove(contactElement);
		entityManger.remove(contactElement.getAddress());
		entityManger.getTransaction().commit();
	    }
}