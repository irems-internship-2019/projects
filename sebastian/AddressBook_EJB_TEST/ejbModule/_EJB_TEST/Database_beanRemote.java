package _EJB_TEST;

import javax.ejb.Remote;

import java.util.List;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.contacts.AddressManager;
import model.contacts.ContactsManager;
//import model.contacts.ContactsModel;
import model.enums.ErrorsEnum;
import services.database.BookContacts;
import services.exceptions.MyCustomException;
@Remote
public interface Database_beanRemote {

	 public List<ContactsManager> loadFromDatabase() throws MyCustomException;
	 public void addNewContactToDatabase(ContactsManager contact) throws MyCustomException;
	 public void updateDatabaseContact(ContactsManager contact) throws MyCustomException;
	 public void deleteDatabaseContact(long deleteIndex) throws MyCustomException;
}

