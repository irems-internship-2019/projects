package services.server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import models.persons.Address;
import models.persons.Contact;
import ui.exceptions.ExceptionsDialogs;

public class ServerServices
{
    private ServerManager manager = new ServerManager();

    private void applyChangesOnServer(String contact) throws ExceptionsDialogs 
    {
	try
	{
	    Statement statement = manager.setConnection();

	    statement.executeUpdate(contact);

	    statement.close();
	} catch (SQLException e)
	{
	    throw new ExceptionsDialogs(e);
	}
    }

    public ArrayList<Contact> getServerContacts() throws ExceptionsDialogs
    {
	ArrayList<Contact> contactElements = new ArrayList<Contact>();
	try
	{
	    Statement statement = manager.setConnection();

	    ResultSet contacts = statement.executeQuery(
		    "SELECT *\r\n" + "FROM contact\r\n" + "INNER JOIN address\r\n" + "ON address_fk = addressid;");

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
	    throw new ExceptionsDialogs(e);
	}
	return contactElements;
    }

    public void addServerContact(Contact contact) throws ExceptionsDialogs
    {
	String insertContact = "with insert_query as (INSERT INTO public.address(\r\n"
		+ "country, city, street, postalcode)\r\n" + "	VALUES ('" + contact.getAddress().getCountry() + "', '"
		+ contact.getAddress().getCity() + "', '" + contact.getAddress().getStreet() + "', '"
		+ contact.getAddress().getPostal_code() + "')" + "returning addressid) INSERT INTO public.contact(\r\n"
		+ "firstname, lastname, phonenumber, emailaddress, address_fk)\r\n" + "	VALUES ('"
		+ contact.getFirstName() + "', '" + contact.getLastName() + "', '" + contact.getPhoneNumber() + "', '"
		+ contact.getEmailAddress() + "', (SELECT addressid FROM insert_query))";

	applyChangesOnServer(insertContact);
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
	String deleteContact = "with delete_query as (DELETE FROM public.contact WHERE contactid = " + contact.getId() + ""
		+ "returning address_fk) DELETE FROM public.address WHERE (addressid) IN (SELECT address_fk FROM delete_query)";

	applyChangesOnServer(deleteContact);
    }
}