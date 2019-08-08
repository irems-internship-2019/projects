package adress.actions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.handlers.HandlerUtil;

import Model.Contact;
import Model.ContactProvider;
import adress.View;
import adress.editor.AdressBookEditor;
import checker.SelectChecker;

public class ViewActionDelegate1 implements IViewActionDelegate {

	private View view;


	@Override
	public void run(IAction action) {
		// TODO Auto-generated method stub
		ISelection selection = view.getSite().getSelectionProvider().getSelection();
		List<Contact> contacts = ContactProvider.INSTANCE.getContacts();
		IStructuredSelection sel = (IStructuredSelection) selection;

		for (Iterator<Contact> iterator = sel.iterator(); iterator.hasNext();) {
			Contact contact = iterator.next();

			contacts.remove(contact);
		}
		view.refresh();
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(IViewPart view) {
		// TODO Auto-generated method stub
		this.view = (View) view;
	}

}
