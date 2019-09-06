package database;

import java.util.ArrayList;

import javax.ejb.Remote;

import model.ContactsTable;

@Remote
public interface ServicesRemote {
 
	  public ArrayList<ContactsTable> loadContatcs();
	  public void insertContact(ContactsTable contact);
	  public void updateContacts(ContactsTable contact);
	  public void deleteContact(Long id);
}
