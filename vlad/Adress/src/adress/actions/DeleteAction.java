package adress.actions;

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

import Model.Contact;
import Model.ContactProvider;
import address.AddressContactsView;
import address.AddressDetailsView;
import adress.editor.AddressBookEditor;
import adress.editor.AddressbookEditorInput;

public class DeleteAction implements IViewActionDelegate {

	private AddressContactsView view;
	IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();

	@Override
	public void run(IAction action) {
		// TODO Auto-generated method stub

		AddressBookEditor editor = (AddressBookEditor) page.getActiveEditor();
		AddressDetailsView detailsView = (AddressDetailsView) page.findView(AddressDetailsView.ID);

		ISelection selection = view.getSite().getSelectionProvider().getSelection();

		List<Contact> contacts = ContactProvider.INSTANCE.getContacts();
		IStructuredSelection sel = (IStructuredSelection) selection;

		
		boolean openQuestion = MessageDialog.openConfirm(Display.getDefault().getActiveShell(), "Delete",
				"Are you sure you want to delete the selected contact?");

		if (openQuestion) {
			for (Iterator<Contact> iterator = sel.iterator(); iterator.hasNext();) {
				Contact contact = iterator.next();

				contacts.remove(contact);

				Contact.contactCounter--;
				view.refresh();
			}
			
			if (detailsView != null && detailsView.equalityCheck(((Contact)sel.getFirstElement()).getIdInt())) {
				
				detailsView.closeView();
				
			}
			System.out.println(editor.getModel()+" = "+ (Contact)sel.getFirstElement());
			if (editor != null && editor.getModel() == view.getSelectedItem())
				editor.closePage();  
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
