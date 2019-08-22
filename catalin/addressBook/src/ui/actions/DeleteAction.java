package ui.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;

import models.persons.Contact;
import models.persons.Contact.ContactElements;
import services.editor.AddressBookEditor;
import services.server.ServerServices;
import ui.views.AddressBookDetailsView;
import ui.views.AddressBookView;

public class DeleteAction implements IViewActionDelegate
{
    private AddressBookView view;

    @Override
    public void init(IViewPart view)
    {
	this.view = (AddressBookView) view;
    }

    @Override
    public void run(IAction action)
    {
	ContactElements persons = ContactElements.INSTANCE;
	ServerServices manager = new ServerServices();
	
	boolean openQuestion = MessageDialog.openQuestion(Display.getDefault().getActiveShell(), "Delete",
		"Do you want to remove?");

	if (openQuestion)
	{   
	    IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
	    AddressBookEditor editor = (AddressBookEditor) activePage.getActiveEditor();
	    AddressBookDetailsView addressBookDetailsView = (AddressBookDetailsView) activePage
		    .findView(AddressBookDetailsView.ID);

	    Contact deletableContact = view.getSelectedItem();
	    persons.getContacts().remove(deletableContact);
	    manager.deleteServerContact(deletableContact);

	    view.refresh();

	    if (editor != null && editor.getModel() == deletableContact)
		activePage.setEditorAreaVisible(false);

	    if (addressBookDetailsView != null && addressBookDetailsView.getDetailsViewContact().contains(deletableContact))
		activePage.hideView(addressBookDetailsView);
	}
    }

    @Override
    public void selectionChanged(IAction action, ISelection selection)
    {
    }
}