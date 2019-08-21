package rcpbook.contacts;

import java.util.ArrayList;
import java.util.List;

import rcpbook.database.DatabaseManager;

public class ContactsModel
{

    private static List<ContactsManager> contacts = new ArrayList<ContactsManager>();

    public List<ContactsManager> getElements()
    {
	return contacts;
    }

    public void addNewContact(ContactsManager newEntry)
    {
	contacts.add(newEntry);
	// dbs.getNextIndex(newEntry);
	DatabaseManager dbs = new DatabaseManager();

	if (newEntry.getIntId() > dbs.checkContactsMaxId())
	{
	    dbs.addNewContactToDatabase(newEntry);
	    
	    ContactsModel newContact = new ContactsModel();
	    newContact.clearContactsList();
	    
	    dbs.loadFromDatabase();
	}

    }

    public int returnLastId()
    {
	return contacts.get(contacts.size() - 1).getIntId();
    }

    public void clearContactsList()
    {
	contacts.clear();
    }
}