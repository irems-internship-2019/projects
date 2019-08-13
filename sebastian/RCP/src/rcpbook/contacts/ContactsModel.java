package rcpbook.contacts;

import java.util.ArrayList;
import java.util.List;

public class ContactsModel
{
    //ViewerTools vTools = new ViewerTools();
    private static int i = 0;

    private static List<ContactsManager> contacts = new ArrayList<ContactsManager>();

    public List<ContactsManager> getElements()
    {
	return contacts;
    }

    public void addNewContact(ContactsManager newEntry)
    {
	contacts.add(newEntry);
    }

    public void rearrangeContactsArray()
    {

	for (ContactsManager contact : contacts)
	{
	    contact.setIDForDelete(i + 1);
	    i++;
	}
	i = 0;

    }
}