package rcpbook.view;

import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import rcpbook.contacts.ContactsManager;
import rcpbook.table.CreateContactUi;

public class ContactsView extends ViewPart {
	public static final String ID = "RCPBook.view";
	private CreateContactUi createNewTable = new CreateContactUi();
	private ViewerTools vTools = new ViewerTools();
	

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new GridLayout(2, false));
		createNewTable.CreateTableUI(parent);
	}


	@Override
	public void setFocus() {
	}
	
	public ContactsManager getSelectedItem()
	{
		final StructuredSelection selection = (StructuredSelection) vTools.getContactsViewer().getSelection();
		final ContactsManager selectedContact = (ContactsManager) selection.getFirstElement();
		
		return selectedContact;
	}
}