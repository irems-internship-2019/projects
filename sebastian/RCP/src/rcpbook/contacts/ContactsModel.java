package rcpbook.contacts;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;

import rcpbook.editor.CheckSelected;
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

	public void deleteSelectedContact() {
		if (CheckSelected.getSelectedItem() != null) {
			contacts.remove(contacts.get(CheckSelected.getSelectedItem().getIdForComparator() - 1));
			vTools.refreshContactsViewer();
		} else
			MessageDialog.openError(Display.getDefault().getActiveShell(), "Delete Contact", "Nothing selected");
	}

}