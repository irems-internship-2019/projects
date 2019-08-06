package addressbook.command;

import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

import addressbook.persons.Contact;
import addressbook.persons.Contact.ContactElements;
import addressbook.table.TableForAddressBookDetails;
import addressbook.view.AddressBookDetailsView;
import addressbook.view.AddressBookView;

public class DeleteContactHandler extends AbstractHandler {
	@SuppressWarnings("unchecked")
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
		IWorkbenchPage page = window.getActivePage();
		AddressBookView view = (AddressBookView) page.findView(AddressBookView.ID);
		AddressBookDetailsView secondView = (AddressBookDetailsView) page.findView(AddressBookDetailsView.ID);
		ISelection selection = view.getSite().getSelectionProvider().getSelection();

		if (selection != null && selection instanceof IStructuredSelection) {
			ArrayList<Contact> persons = ContactElements.INSTANCE.getContacts();
			ArrayList<Contact> persons2 = TableForAddressBookDetails.contactDetailsList;
			IStructuredSelection sel = (IStructuredSelection) selection;

			for (Iterator<Contact> iterator = sel.iterator(); iterator.hasNext();) {
				Contact person = iterator.next();
				persons.remove(person);
				persons2.remove(person);
			}
			view.getViewer().refresh();
			secondView.getViewer().refresh();
		}
		return null;
	}
}