package ui.actions;

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
import org.eclipse.ui.PlatformUI;

import database.ServicesRemote;
import ejb.EJBinit;
import model.ContactsTable;
import ui.editors.AddressBookEditor;
import ui.views.AddressContactsView;
import ui.views.AddressDetailsView;

public class DeleteAction implements IViewActionDelegate {

	private AddressContactsView view;
	IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
	EJBinit bean = new EJBinit();
	ServicesRemote beanRemote = bean.beanInit();
	@Override
	public void run(IAction action) {
		// TODO Auto-generated method stub

		AddressBookEditor editor = (AddressBookEditor) page.getActiveEditor();
		AddressDetailsView detailsView = (AddressDetailsView) page.findView(AddressDetailsView.ID);

		ISelection selection = view.getSite().getSelectionProvider().getSelection();
		IStructuredSelection sel = (IStructuredSelection) selection;

		boolean openQuestion = MessageDialog.openConfirm(Display.getDefault().getActiveShell(), "Delete",
				"Are you sure you want to delete the selected contact?");

		if (openQuestion) {
			for (Iterator<ContactsTable> iterator = sel.iterator(); iterator.hasNext();) {
				ContactsTable contact = iterator.next();

				beanRemote.deleteContact(contact.getContactid());
			 	view.refreshViewer();
			}

			if (detailsView != null && detailsView.equalityCheck(((ContactsTable) sel.getFirstElement()).getContactid()))

				detailsView.closeView();
		}

	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(IViewPart view) {
		// TODO Auto-generated method stub
		this.view = (AddressContactsView) view;
	}

}
