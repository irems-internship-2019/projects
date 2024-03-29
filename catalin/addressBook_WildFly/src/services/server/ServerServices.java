package services.server;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import models.persons.Address;
import models.persons.Contact;
import ui.exceptions.ExceptionsDialogs;

public class ServerServices
{
   private ServerManager manager = new ServerManager();
    
    public ArrayList<Contact> getServerContacts() throws ExceptionsDialogs
    {
	ArrayList<Contact> contactElements = new ArrayList<Contact>();
	try
	{
	    EntityManager entityManger = manager.setConnection();

	    Query q = entityManger.createQuery("select c from ContactTable c");
	    List<ContactTable> contactElemnets = q.getResultList();
	    for (ContactTable contactElement : contactElemnets)
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
	    throw new ExceptionsDialogs();
	}
	return contactElements;
    }

    public void addServerContact(Contact contact) throws ExceptionsDialogs
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

    public void editServerContact(Contact contact) throws ExceptionsDialogs
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

    public void deleteServerContact(Contact contact) throws ExceptionsDialogs
    {
	EntityManager entityManger = manager.setConnection();

	ContactTable contactElement = entityManger.find(ContactTable.class, contact.getId());

	entityManger.getTransaction().begin();
	entityManger.remove(contactElement);
	entityManger.remove(contactElement.getAddress());
	entityManger.getTransaction().commit();
    }
}