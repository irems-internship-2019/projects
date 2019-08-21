package rcpbook.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;

import rcpbook.contacts.AddressManager;
import rcpbook.contacts.ContactsManager;
import rcpbook.contacts.ContactsModel;

public class DatabaseManager
{
    private Connection connection;
    private ContactsModel newContact = new ContactsModel();

    private int currentDatabasePK = 0;

    public boolean establishConnection()
    {
	try
	{
	    connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "sebi");
	    return true;
	} catch (SQLException e)
	{
	    MessageDialog.openInformation(Display.getDefault().getActiveShell(), "ERROR",
		    "Could not establish connection");
	    e.printStackTrace();
	    return false;
	}
    }

    public void loadFromDatabase()
    {
	Statement statement;
	if (!establishConnection())
	    return;

	try
	{

	    statement = connection.createStatement();
	    ResultSet contactsSet = statement.executeQuery("SELECT * FROM public.contacts a\r\n" + 
	    	"FULL JOIN public.addresses b ON a.address_fk = b.address_id ORDER BY a.contacts_id;");
//	    
//	    statement2 = connection.createStatement();
//	    ResultSet addressesSet = statement2.executeQuery("SELECT * FROM public.addresses WHERE address_id > "
//		    + currentDatabasePK + " ORDER BY address_id LIMIT 2");

	    while (contactsSet.next())
	    {
		ContactsManager newCont = new ContactsManager(contactsSet.getString("first_name"),
			contactsSet.getString("last_name"),
			new AddressManager(contactsSet.getString("country"), contactsSet.getString("city"),
				contactsSet.getString("street"), contactsSet.getString("postal_code")),
			contactsSet.getString("phone_number"), contactsSet.getString("email"));

		newCont.setPrimaryKeyID(Integer.parseInt(contactsSet.getString("contacts_id")));

		newContact.addNewContact(newCont);

	    }
	} catch (SQLException e)
	{
	    MessageDialog.openInformation(Display.getDefault().getActiveShell(), "ERROR", "SQL QUERY FAILED!");
	    e.printStackTrace();
	}

    }

    public int checkContactsMaxId()
    {
	int max = 0;
	Statement statement;

	if (!establishConnection())
	    return max;
	try
	{
	    statement = connection.createStatement();
	    ResultSet maxID = statement.executeQuery("SELECT MAX(contacts_id) FROM public.contacts");
	    
	    if (maxID.next())
	    {
		max = maxID.getInt(1);
	    }

	    return max;

	} catch (SQLException e)
	{
	    MessageDialog.openInformation(Display.getDefault().getActiveShell(), "ERROR", "Could not get max!");
	    e.printStackTrace();
	}
	return max;
    }
    
    public int checkAddressesMaxId()
    {
	int max = 0;
	Statement statement;

	if (!establishConnection())
	    return max;
	try
	{
	    statement = connection.createStatement();
	    ResultSet maxID = statement.executeQuery("SELECT MAX(address_id) FROM public.addresses");
	    
	    if (maxID.next())
	    {
		max = maxID.getInt(1);
	    }

	    return max;

	} catch (SQLException e)
	{
	    MessageDialog.openInformation(Display.getDefault().getActiveShell(), "ERROR", "Could not get max!");
	    e.printStackTrace();
	}
	return max;
    }

    public void addNewContactToDatabase(ContactsManager contact)
    {
	Statement statement, statement2,sst;

	if (!establishConnection())
	    return;

	try
	{
	     statement2 = connection.createStatement();
	     String sql = "INSERT INTO public.addresses(\r\n" + "country, city, street, postal_code)\r\n"
		    + "	VALUES ('" + contact.getAddress().getCountry() + "', '"
		    + contact.getAddress().getCity() + "', '" + contact.getAddress().getStreet() + "','"
		    + contact.getAddress().getPostalCode() + "')";
	    statement2.executeUpdate(sql);

	    statement = connection.createStatement();
	     sql = "INSERT INTO public.contacts(\r\n"
		    + "first_name, last_name, phone_number, email, address_fk)\r\n" + "VALUES ('"
		    + contact.getFirstName() + "', '" + contact.getLastName() + "', '"
		    + contact.getPhone() + "','" + contact.getEmail() + "','"+checkAddressesMaxId()+"')";
	    statement.executeUpdate(sql);

	  // loadFromDatabase();

	} catch (SQLException e)
	{
	    MessageDialog.openInformation(Display.getDefault().getActiveShell(), "ERROR",
		    "Could not insert into table!");
	    e.printStackTrace();
	}

    }

    public void updateDatabaseContact(ContactsManager contact)
    {
	Statement statement, statement2;
	if (!establishConnection())
	    return;

	try
	{
	    statement = connection.createStatement();
	    String sql = "UPDATE public.contacts\r\n" + "SET first_name='"
		    + contact.getFirstName() + "', last_name='" + contact.getLastName() + "', phone_number='"
		    + contact.getPhone() + "', email='" + contact.getEmail() + "'\r\n" + "WHERE  contacts_id= "
		    + contact.getIntId() + "";
	    statement.executeUpdate(sql);

	    statement2 = connection.createStatement();
	    sql = "UPDATE public.addresses\r\n" + "SET country='"
		    + contact.getAddress().getCountry() + "', city='" + contact.getAddress().getCity() + "', street='"
		    + contact.getAddress().getStreet() + "', postal_code='" + contact.getAddress().getPostalCode()
		    + "'WHERE address_id = " + contact.getIntId() + "";
	    statement2.executeUpdate(sql);
	} catch (SQLException e)
	{
	    MessageDialog.openInformation(Display.getDefault().getActiveShell(), "ERROR",
		    "Could not update selected Element!");
	    e.printStackTrace();
	}
    }

    public void deleteDatabaseContact(int deleteIndex)
    {
	Statement statement, statement2;

	if (!establishConnection())
	    return;

	try
	{
//	    statement = connection.createStatement();
//	    String sql = "DELETE FROM public.contacts\r\n" + "WHERE contacts_id= " + deleteIndex + "";
//	    statement.executeUpdate(sql);

	    statement2 = connection.createStatement();
	    String sql = "DELETE FROM public.addresses WHERE address_id = \r\n" + 
	    	 "(SELECT address_fk FROM public.contacts WHERE contacts_id = "+deleteIndex+")";
	    statement2.executeUpdate(sql);
	} catch (SQLException e)
	{
	    MessageDialog.openInformation(Display.getDefault().getActiveShell(), "ERROR",
		    "Could not delete selected Contact!");
	    e.printStackTrace();
	}
    }

    public void loadNextSetOfContacts()
    {
	if (newContact.returnLastId() != checkContactsMaxId())
	{

	    currentDatabasePK = newContact.returnLastId();
	    newContact.clearContactsList();
	    loadFromDatabase();
	}
    }

    public void loadPreviousSetOfContacts()
    {

    }
}