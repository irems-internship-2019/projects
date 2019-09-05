package addressejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import models.persons.Address;
import models.persons.Contact;
import services.server.ServerManager;

/**
 * Session Bean implementation class Services
 */
@Stateless
@LocalBean
public class Services implements ServicesRemote {
	 private ServerManager manager = new ServerManager();

	 @Override
	 @SuppressWarnings("unchecked")
		public List<Contact> getServerContacts() 
	    {
	    	 List<Contact> localContacts = new ArrayList<Contact>();
		try
		{
		    EntityManager entityManger = manager.setConnection();

		    Query q = entityManger.createQuery("SELECT c FROM Contact c");
		    List<Contact> contacts = q.getResultList();
		    for (Contact contact : contacts)
		    {

		    	Contact newContact = new Contact(contact.getContactid(), contact.getFirstName(),
				contact.getLastName(),
				new Address(contact.getAddress().getCountry(), contact.getAddress().getCity(),
					contact.getAddress().getStreet(), contact.getAddress().getPostalCode()),
				contact.getPhoneNumber(), contact.getEmailAddress());

		    	localContacts.add(newContact);
		    } 
		} catch (Exception e)
		{
		}
		  return localContacts;
	    }
	 
	 @Override
	 public void addServerContact(Contact contact)
	    {
		EntityManager entityManger = manager.setConnection();

		entityManger.getTransaction().begin();
		Contact contactElement = new Contact();
		Address addressElement = new Address();

		addressElement.setCountry(contact.getAddress().getCountry());
		addressElement.setCity(contact.getAddress().getCity());
		addressElement.setStreet(contact.getAddress().getStreet());
		addressElement.setPostalCode(contact.getAddress().getPostalCode());
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

		Contact contactElement = entityManger.find(Contact.class, contact.getContactid());

		entityManger.getTransaction().begin();

		contactElement.setFirstname(contact.getFirstName());
		contactElement.setLastName(contact.getLastName());
		contactElement.setPhoneNumber(contact.getPhoneNumber());
		contactElement.setEmailAddress(contact.getEmailAddress());
		contactElement.getAddress().setCountry(contact.getAddress().getCountry());
		contactElement.getAddress().setCity(contact.getAddress().getCity());
		contactElement.getAddress().setStreet(contact.getAddress().getStreet());
		contactElement.getAddress().setPostalCode(contact.getAddress().getPostalCode());

		entityManger.getTransaction().commit();
	    }
	 
	 @Override
	 public void deleteServerContact(Contact contact)
	    {
		EntityManager entityManger = manager.setConnection();

		Contact contactElement = entityManger.find(Contact.class, contact.getContactid());

		entityManger.getTransaction().begin();
		entityManger.remove(contactElement);
		entityManger.remove(contactElement.getAddress());
		entityManger.getTransaction().commit();
	    }
}