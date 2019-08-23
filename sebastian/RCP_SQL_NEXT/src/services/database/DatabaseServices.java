package services.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.contacts.AddressManager;
import model.contacts.ContactsManager;
//import model.contacts.ContactsModel;
import model.enums.ErrorsEnum;
import ui.exceptions.MyCustomException;

public class DatabaseServices
{
    // private DatabaseConnection dbsConnect = new DatabaseConnection();
    private DatabaseConnection dbsConnect = new DatabaseConnection();
   // private ContactsModel newContact = new ContactsModel();

    public List<ContactsManager> loadFromDatabase() throws MyCustomException
    {
	List<ContactsManager> localContactsList = new ArrayList<ContactsManager>();

	try
	{
	    ResultSet contactsSet = dbsConnect.establishConnection().executeQuery("SELECT * FROM public.contacts a\r\n"
		    + "FULL JOIN public.addresses b ON a.address_fk = b.address_id ORDER BY a.contacts_id;");

	    while (contactsSet.next())
	    {
		ContactsManager newCont = new ContactsManager(contactsSet.getString("first_name"),
			contactsSet.getString("last_name"),
			new AddressManager(contactsSet.getString("country"), contactsSet.getString("city"),
				contactsSet.getString("street"), contactsSet.getString("postal_code")),
			contactsSet.getString("phone_number"), contactsSet.getString("email"));

		newCont.setPrimaryKeyID(Integer.parseInt(contactsSet.getString("contacts_id")));

               localContactsList.add(newCont);
	    }

	    return localContactsList;

	} catch (SQLException e)
	{
	    throw new MyCustomException(e, ErrorsEnum.LOAD);
	}

    }

    public void addNewContactToDatabase(ContactsManager contact) throws MyCustomException
    {

	try
	{

	    String sql = " with insert_query as (INSERT INTO public.addresses (country, city, street, postal_code)"
		    + "VALUES ('" + contact.getAddress().getCountry() + "','" + contact.getAddress().getCity() + "','"
		    + contact.getAddress().getStreet() + "','" + contact.getAddress().getPostalCode() + "')"
		    + "returning address_id) INSERT INTO public.contacts (first_name, last_name, phone_number, email, address_fk)"
		    + "VALUES ('" + contact.getFirstName() + "','" + contact.getLastName() + "','" + contact.getPhone()
		    + "','" + contact.getEmail() + "',(select address_id from insert_query))";
	    dbsConnect.establishConnection().executeUpdate(sql);

	} catch (SQLException e)
	{

	    throw new MyCustomException(e, ErrorsEnum.INSERT);
	}

    }

    public void updateDatabaseContact(ContactsManager contact) throws MyCustomException
    {

	try
	{

	    String sql = "with update_query as (UPDATE public.contacts SET first_name= '" + contact.getFirstName()
		    + "', last_name='" + contact.getLastName() + "', phone_number='" + contact.getPhone() + "', email='"
		    + contact.getEmail() + "' WHERE contacts_id = " + contact.getIntId() + ""
		    + "returning address_fk) UPDATE public.addresses SET  country ='"
		    + contact.getAddress().getCountry() + "' , city ='" + contact.getAddress().getCity()
		    + "' , street ='" + contact.getAddress().getStreet() + "' , postal_code ='"
		    + contact.getAddress().getPostalCode()
		    + "'WHERE (address_id) IN (select address_fk from update_query)";

	    dbsConnect.establishConnection().executeUpdate(sql);

	} catch (SQLException e)
	{

	    throw new MyCustomException(e, ErrorsEnum.UPDATE);
	}
    }

    public void deleteDatabaseContact(int deleteIndex) throws MyCustomException
    {
	try
	{
	    String sql = "with delete_query as (DELETE FROM public.contacts WHERE contacts_id = " + deleteIndex
		    + "returning address_fk) DELETE FROM public.addresses WHERE (address_id) IN (SELECT address_fk FROM delete_query)";
	    dbsConnect.establishConnection().executeUpdate(sql);
	} catch (SQLException e)
	{

	    throw new MyCustomException(e, ErrorsEnum.DELETE);
	}
    }
}