package _EJB_TEST;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

//import model.contacts.AddressManager;
//import model.contacts.ContactsManager;
//import model.contacts.ContactsModel;
import model.enums.ErrorsEnum;
import services.database.BookAddresses;
import services.database.BookContacts;
import services.database.JpaEntityMgr;
//import simple.model.Todo;
//import services.exceptions.MyCustomException;

/**
 * Session Bean implementation class Ui_bean
 */
@Stateless
@LocalBean
public class Database_bean implements Database_beanRemote
{

    /**
     * Default constructor.
     */

    private JpaEntityMgr dbs = new JpaEntityMgr();

    public Database_bean()
    {
	// TODO Auto-generated constructor stub
    }

    @Override
    public List<BookContacts> loadFromDatabase()
    {
	System.out.println("###################################################---CORINA---######################################################");
	List<BookContacts> localContactsList = new ArrayList<BookContacts>();
	try
	{
	    Query q = dbs.EntityMgr().createQuery("SELECT c FROM BookContacts c");
	    List<BookContacts> contactsList = q.getResultList();
	    for (BookContacts contact : contactsList)
	    {

		BookContacts newCont = new BookContacts(contact.getContacts_id(), contact.getFirst_name(),
			contact.getLast_name(),
			new BookAddresses(contact.getAddress().getCountry(), contact.getAddress().getCity(),
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

    @Override
    public void addNewContactToDatabase(BookContacts contact)
    {
	try
	{
	    EntityManager em = dbs.EntityMgr();

	    BookContacts contactPersist = new BookContacts();

	    em.persist(contact.getAddress());

	    contactPersist.setFirst_name(contact.getFirst_name());
	    contactPersist.setLast_name(contact.getLast_name());
	    contactPersist.setPhone_number(contact.getPhone_number());
	    contactPersist.setEmail(contact.getEmail());
	    contactPersist.setAddress(contact.getAddress());

	    em.persist(contactPersist);
	} catch (Exception e)
	{
	    e.printStackTrace();
	    // throw new MyCustomException(e, ErrorsEnum.INSERT);
	}

    }

    @Override
    public void updateDatabaseContact(BookContacts contact)
    {
	try
	{
	    EntityManager em = dbs.EntityMgr();
	    BookContacts transactionContact = em.find(BookContacts.class, contact.getLongID());

	    em.getTransaction().begin();
	    transactionContact.setFirst_name(contact.getFirst_name());
	    transactionContact.setLast_name(contact.getLast_name());
	    transactionContact.setPhone_number(contact.getPhone_number());
	    transactionContact.setEmail(contact.getEmail());

	    transactionContact.getAddress().setCountry(contact.getAddress().getCountry());
	    transactionContact.getAddress().setCity(contact.getAddress().getCity());
	    transactionContact.getAddress().setStreet(contact.getAddress().getStreet());
	    transactionContact.getAddress().setPostal_code(contact.getAddress().getPostal_code());

	    em.getTransaction().commit();

	} catch (Exception e)
	{
	    e.printStackTrace();
	    // throw new MyCustomException(e, ErrorsEnum.UPDATE);
	}

    }

    @Override
    public void deleteDatabaseContact(long deleteIndex)
    {
	try
	{

	    EntityManager em = dbs.EntityMgr();

	    BookContacts contact = em.find(BookContacts.class, deleteIndex);

	    // em.getTransaction().begin();

	    em.remove(contact);
	    em.remove(contact.getAddress());

	    // em.getTransaction().commit();

//	    EntityManager em = dbs.EntityMgr();
//
//	    BookAddresses a = em.find(entityClass, primaryKey)
//	    BookContacts p = em.find(BookContacts.class, deleteIndex);
//	    if (p != null)
//		em.remove(p);

	} catch (Exception e)
	{
	    e.printStackTrace();
	    // throw new MyCustomException(e, ErrorsEnum.DELETE);
	}

    }

}
