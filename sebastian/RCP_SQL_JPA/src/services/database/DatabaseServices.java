package services.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.contacts.AddressManager;
import model.contacts.ContactsManager;
//import model.contacts.ContactsModel;
import model.enums.ErrorsEnum;
import services.database.BookContacts;
//import simple.model.Todo;
import ui.exceptions.MyCustomException;


public class DatabaseServices
{
    private JpaEntityMgr dbs = new JpaEntityMgr();
 
    public List<ContactsManager> loadFromDatabase() throws MyCustomException
    {
	List<ContactsManager> localContactsList = new ArrayList<ContactsManager>();
	try
	{
	    Query q = dbs.EntityMgr().createQuery("SELECT c  FROM BookContacts c");
	    @SuppressWarnings("unchecked")
	    List<BookContacts> contactsList = q.getResultList();
	    for (BookContacts contact : contactsList)
	    {
		ContactsManager newCont = new ContactsManager(contact.getContacts_id(), contact.getFirst_name(),
			contact.getLast_name(),
			new AddressManager(contact.getAddress().getCountry(), contact.getAddress().getCity(),
				contact.getAddress().getStreet(), contact.getAddress().getPostal_code()),
			contact.getPhone_number(), contact.getEmail());

		localContactsList.add(newCont);
	    }
	} catch (Exception e)
	{
	    e.printStackTrace();
	}
	return localContactsList;
    }

    public void addNewContactToDatabase(ContactsManager contact) throws MyCustomException
    {
	try
	{
	    EntityManager em = dbs.EntityMgr();
	    
	    em.getTransaction().begin();
	    BookAddresses addressPersist = new BookAddresses();
	    BookContacts contactPersist = new BookContacts();

	    addressPersist.setCountry(contact.getAddress().getCountry());
	    addressPersist.setCity(contact.getAddress().getCity());
	    addressPersist.setStreet(contact.getAddress().getStreet());
	    addressPersist.setPostal_code(contact.getAddress().getPostalCode());

	    contactPersist.setFirst_name(contact.getFirstName());
	    contactPersist.setLast_name(contact.getLastName());
	    contactPersist.setPhone_number(contact.getPhone());
	    contactPersist.setEmail(contact.getEmail());
	    contactPersist.setAddress(addressPersist);

	    em.persist(contactPersist);
	    em.persist(addressPersist);
	    em.getTransaction().commit();

	    em.close();

	} catch (Exception e)
	{

	    throw new MyCustomException(e, ErrorsEnum.INSERT);
	}

    }

    public void updateDatabaseContact(ContactsManager contact) throws MyCustomException
    {

	try
	{
	    EntityManager em = dbs.EntityMgr();
	    BookContacts transactionContact = em.find(BookContacts.class, contact.getLongID());
	  

	    em.getTransaction().begin();
	    transactionContact.setFirst_name(contact.getFirstName());
	    transactionContact.setLast_name(contact.getLastName());
	    transactionContact.setPhone_number(contact.getPhone());
	    transactionContact.setEmail(contact.getEmail());
	    
	    transactionContact.getAddress().setCountry(contact.getAddress().getCountry());
	    transactionContact.getAddress().setCity(contact.getAddress().getCity());
	    transactionContact.getAddress().setStreet(contact.getAddress().getStreet());
	    transactionContact.getAddress().setPostal_code(contact.getAddress().getPostalCode());
	    
	    em.getTransaction().commit();


	} catch (Exception e)
	{

	    throw new MyCustomException(e, ErrorsEnum.UPDATE);
	}
    }

    public void deleteDatabaseContact(long deleteIndex) throws MyCustomException
    {
	try
	{
	    EntityManager em = dbs.EntityMgr();
	    
	    BookContacts contact = em.find(BookContacts.class, deleteIndex);

	    em.getTransaction().begin();
	    em.remove(contact.getAddress());
	    em.remove(contact);
	    em.getTransaction().commit();
	    
	} catch (Exception e)
	{

	    throw new MyCustomException(e, ErrorsEnum.DELETE);
	}
    }
}