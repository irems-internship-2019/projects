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
	    ResultSet contactsSet = statement.executeQuery("SELECT * FROM public.contacts a\r\n"
		    + "FULL JOIN public.addresses b ON a.address_fk = b.address_id ORDER BY a.contacts_id;");

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

    public void addNewContactToDatabase(ContactsManager contact)
    {
	Statement statement;

	if (!establishConnection())
	    return;

	try
	{
	    statement = connection.createStatement();
	    String sql = " with insert_query as (INSERT INTO public.addresses (country, city, street, postal_code)"
		    + "VALUES ('" + contact.getAddress().getCountry() + "','" + contact.getAddress().getCity() + "','"
		    + contact.getAddress().getStreet() + "','" + contact.getAddress().getPostalCode() + "')"
		    + "returning address_id) INSERT INTO public.contacts (first_name, last_name, phone_number, email, address_fk)"
		    + "VALUES ('" + contact.getFirstName() + "','" + contact.getLastName() + "','" + contact.getPhone()
		    + "','" + contact.getEmail() + "',(select address_id from insert_query))";
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
	Statement statement;
	if (!establishConnection())
	    return;

	try
	{
	    statement = connection.createStatement();
	    String sql = "with update_query as (UPDATE public.contacts SET first_name= '" + contact.getFirstName()
		    + "', last_name='" + contact.getLastName() + "', phone_number='" + contact.getPhone() + "', email='"
		    + contact.getEmail() + "' WHERE contacts_id = " + contact.getIntId() + ""
		    + "returning address_fk) UPDATE public.addresses SET  country ='"
		    + contact.getAddress().getCountry() + "' , city ='" + contact.getAddress().getCity()
		    + "' , street ='" + contact.getAddress().getStreet() + "' , postal_code ='"
		    + contact.getAddress().getPostalCode()
		    + "'WHERE (address_id) IN (select address_fk from update_query)";

	    statement.executeUpdate(sql);

	} catch (SQLException e)
	{
	    MessageDialog.openInformation(Display.getDefault().getActiveShell(), "ERROR",
		    "Could not update selected Element!");
	    e.printStackTrace();
	}
    }

    public void deleteDatabaseContact(int deleteIndex)
    {
	Statement statement;

	if (!establishConnection())
	    return;

	try
	{

	    statement = connection.createStatement();
	    String sql = "DELETE FROM public.addresses WHERE address_id = \r\n"
		    + "(SELECT address_fk FROM public.contacts WHERE contacts_id = " + deleteIndex + ")";
	    statement.executeUpdate(sql);
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