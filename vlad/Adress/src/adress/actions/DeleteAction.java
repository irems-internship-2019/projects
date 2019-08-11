package adress.actions;

import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;

import Model.Contact;
import Model.ContactProvider;
import address.AddressContactsView;
import adress.editor.AddressBookEditor;
import adress.editor.AddressbookEditorInput;

public class DeleteAction implements IViewActionDelegate {

	private AddressContactsView view;
	private AddressBookEditor editor;

	@Override
	public void run(IAction action) {
		// TODO Auto-generated method stub
		IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		ISelection selection = view.getSite().getSelectionProvider().getSelection();
		List<Contact> contacts = ContactProvider.INSTANCE.getContacts();
		IStructuredSelection sel = (IStructuredSelection) selection;

		for (Iterator<Contact> iterator = sel.iterator(); iterator.hasNext();) {
			Contact contact = iterator.next();

			contacts.remove(contact);
			Contact.contactCounter--;

		}
		AddressBookEditor.openEditor(null);
		view.refresh();
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(IViewPart view) {
		// TODO Auto-generated method stub
		this.view = (AddressContactsView) view;
		this.editor= (AddressBookEditor) editor;
	}
	
}
