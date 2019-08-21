package model.contacts;

import java.util.ArrayList;
import java.util.List;

import services.database.DatabaseServices;

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