package addressbook.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import addressbook.persons.Address;
import addressbook.persons.Contact;
import addressbook.persons.Contact.ContactElements;
import addressbook.server.ServerManager;

public class ServerServices
{
    ServerManager manager = new ServerManager();
    
    public void getServerContacts()
    {
	try
	{   
	    Statement statement = manager.setConnection();
	    
	    ResultSet contacts = statement.executeQuery("SELECT *\r\n" + 
	    	"FROM contact\r\n" + 
	    	"INNER JOIN address\r\n" + 
	    	"ON address_fk = addressid;");

	    while (contacts.next())
	    {
		Address address = new Address(contacts.getString("country"), contacts.getString("city"),
			contacts.getString("street"), contacts.getString("postalcode"));
		Contact contact = new Contact(contacts.getString("firstname"), contacts.getString("lastname"), address,
			contacts.getString("phonenumber"), contacts.getString("emailaddress"));
		contact.setId(Integer.parseInt(contacts.getString("contactid")));
		ContactElements.INSTANCE.createElements(contact);
	    }
	} catch (SQLException e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }
    
    public void addServerContact(Contact contact)
    {
	try
	{    
	    Statement statement = manager.setConnection();
	    Statement secondStatement = manager.setConnection();
	    
	    String setContact = "INSERT INTO public.contact(\r\n"
		    + "firstname, lastname, phonenumber, emailaddress)\r\n" + "	VALUES ('" 
		    + contact.getFirstName() + "', '" + contact.getLastName() + "', '"
		    + contact.getPhoneNumber() + "', '" + contact.getEmailAddress() + "');";

	    String setAddress = "INSERT INTO public.address(\r\n"
		    + "country, city, street, postalcode)\r\n" + "	VALUES ('"
		    + contact.getAddress().getCountry() + "', '" + contact.getAddress().getCity() + "', '"
		    + contact.getAddress().getStreet() + "', '" + contact.getAddress().getPostal_code() + "');";

	    statement.executeUpdate(setContact);
	    secondStatement.executeUpdate(setAddress);

	    statement.close();
	    secondStatement.close();
	} catch (SQLException e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }
    
    public void editServerContact(Contact contact)
    {
	try
	{
	    Statement statement = manager.setConnection();
	    Statement secondStatement = manager.setConnection();
	    
	    String updateContact = "UPDATE contact set firstname = '" + contact.getFirstName() + "', lastname = '"
		    + contact.getLastName() + "', phonenumber = '" + contact.getPhoneNumber() + "', emailaddress = '"
		    + contact.getEmailAddress() + "' where contactid = " + contact.getId() + ";";

	    String updateAddress = "UPDATE address set country = '" + contact.getAddress().getCountry() + "', city = '"
		    + contact.getAddress().getCity() + "', street = '" + contact.getAddress().getStreet()
		    + "', postalcode = '" + contact.getAddress().getPostal_code() + "' where addressid = "
		    + contact.getId() + ";";

	    statement.executeUpdate(updateContact);
	    secondStatement.executeUpdate(updateAddress);
	    
	    statement.close();
	    secondStatement.close();
	} catch (SQLException e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }
    
    public void deleteServerContact(Contact contact)
    {
	try
	{
	    Statement statement = manager.setConnection();
	    Statement secondStatement = manager.setConnection();
	    
	    String deleteContact = "DELETE from contact where contactid = " + contact.getId() + ";";
	    String deleteAddress = "DELETE from address where addressid = " + contact.getId() + ";";

	    statement.executeUpdate(deleteContact);
	    secondStatement.executeUpdate(deleteAddress);

	    statement.close();
	    secondStatement.close();
	} catch (SQLException e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }
}
