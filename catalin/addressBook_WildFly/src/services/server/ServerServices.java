package services.server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import models.persons.Address;
import models.persons.Contact;
import ui.exceptions.ExceptionsDialogs;

public class ServerServices
{
    private ServerManager manager = new ServerManager();
    private static final String PERSISTENCE_UNIT_NAME = "contacts";
    private static EntityManagerFactory factory;
    private EntityManager em = factory.createEntityManager();

    private void applyChangesOnServer(String contact) throws ExceptionsDialogs
    {
	try
	{
	    Statement statement = manager.setConnection();

	    statement.executeUpdate(contact);

	    statement.close();
	} catch (SQLException e)
	{
	    throw new ExceptionsDialogs();
	}
    }

    public ArrayList<Contact> getServerContacts() throws ExceptionsDialogs
    {
	ArrayList<Contact> contactElements = new ArrayList<Contact>();
	try
	{
	    Statement statement = manager.setConnection();

	    ResultSet contacts = statement.executeQuery(
		    "SELECT *\r\n" + "FROM addressbook.contact\r\n" + "INNER JOIN addressbook.address\r\n" + "ON address_fk = addressid;");

	    while (contacts.next())
	    {
		Address address = new Address(contacts.getString("country"), contacts.getString("city"),
			contacts.getString("street"), contacts.getString("postalcode"));
		Contact contact = new Contact(contacts.getString("firstname"), contacts.getString("lastname"), address,
			contacts.getString("phonenumber"), contacts.getString("emailaddress"));
		contact.setId(Integer.parseInt(contacts.getString("contactid")));
		contactElements.add(contact);
	    }
	} catch (SQLException e)
	{
	    throw new ExceptionsDialogs();
	}
	return contactElements;
    }

    public void addServerContact(Contact contact) throws ExceptionsDialogs
    {
	factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

	em.getTransaction().begin();

	AddressTable addressElement = new AddressTable();
	addressElement.setCountry(contact.getAddress().getCountry());
	addressElement.setCity(contact.getAddress().getCity());
	addressElement.setStreet(contact.getAddress().getStreet());
	addressElement.setPostalCode(contact.getAddress().getPostal_code());
	em.persist(addressElement);
	
	ContactTable contactElement = new ContactTable();
	contactElement.setFirstname(contact.getFirstName());
	contactElement.setLastName(contact.getLastName());
	contactElement.setPhoneNumber(contact.getPhoneNumber());
	contactElement.setEmailAddress(contact.getEmailAddress());
	contactElement.setAddress(addressElement);
	em.persist(contactElement);

	em.getTransaction().commit();
	em.close();
    }

    public void editServerContact(Contact contact) throws ExceptionsDialogs
    {
	String updateContact = "with update_query as (UPDATE public.contact SET firstname = '" + contact.getFirstName()
		+ "', lastname = '" + contact.getLastName() + "', phonenumber = '" + contact.getPhoneNumber()
		+ "', emailaddress = '" + contact.getEmailAddress() + "' WHERE contactid = " + contact.getId() + ""
		+ "returning address_fk) UPDATE address SET country = '" + contact.getAddress().getCountry()
		+ "', city = '" + contact.getAddress().getCity() + "', street = '" + contact.getAddress().getStreet()
		+ "', postalcode = '" + contact.getAddress().getPostal_code()
		+ "' WHERE (addressid) IN (SELECT address_fk FROM update_query)";

	applyChangesOnServer(updateContact);
    }

    public void deleteServerContact(Contact contact) throws ExceptionsDialogs
    {
	ContactTable contactElement = em.find(ContactTable.class, contact.getId());

	  em.getTransaction().begin();
	  em.remove(contactElement);
	  em.getTransaction().commit();
    }
}