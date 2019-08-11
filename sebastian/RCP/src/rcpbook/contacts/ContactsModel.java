package rcpbook.contacts;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;

import rcpbook.view.ViewerTools;

public class ContactsModel {
	ViewerTools vTools = new ViewerTools();

	private static List<ContactsManager> contacts = new ArrayList<ContactsManager>();

	public List<ContactsManager> getElements() {
		return contacts;
	}

	public void addNewContact(ContactsManager newEntry) {
		contacts.add(newEntry);
	}
	
	public void addContactAtIndex(ContactsManager newEntry, int index, int oldContactIndex) {
		contacts.add(index, newEntry);
		//this is used to assign the old index to the new object
		contacts.get(index).setID(oldContactIndex);
		
	}
}