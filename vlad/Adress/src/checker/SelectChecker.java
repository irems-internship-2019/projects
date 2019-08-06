package checker;

import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import Model.Contact;
import Model.ContactProvider;
import adress.View;

public class SelectChecker {

	private IWorkbench wb = PlatformUI.getWorkbench();
	private IWorkbenchWindow win = wb.getActiveWorkbenchWindow();
	private IWorkbenchPage page = win.getActivePage();
	private View view = (View) page.findView(View.ID);
	private ISelection selection = view.getSite().getSelectionProvider().getSelection();

	public boolean isSelected() {

		if (selection.isEmpty() == false && selection instanceof IStructuredSelection) {

			return true;
		}

		return false;
	}

	public void testDel() {

		List<Contact> persons = ContactProvider.INSTANCE.getContacts();
		IStructuredSelection sel = (IStructuredSelection) selection;

		for (Iterator<Contact> iterator = sel.iterator(); iterator.hasNext();) {
			Contact contact = iterator.next();

			persons.remove(contact);
		}
		view.refresh();
	}

	public IWorkbenchPage getPage() {
		return page;

	}

	public ISelection getSSelection() {
		return selection;

	}

	public void refresh() {
		view.refresh();

	}

}
