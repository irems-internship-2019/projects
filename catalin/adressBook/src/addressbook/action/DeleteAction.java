package addressbook.action;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;

import addressbook.editor.AddressBookEditor;
import addressbook.persons.Contact;
import addressbook.persons.Contact.ContactElements;
import addressbook.view.AddressBookDetailsView;
import addressbook.view.AddressBookView;

public class DeleteAction implements IViewActionDelegate
{
    private AddressBookView view;
    private ContactElements persons = ContactElements.INSTANCE;

    @Override
    public void init(IViewPart view)
    {
	this.view = (AddressBookView) view;
    }

    @Override
    public void run(IAction action)
    {
	boolean openQuestion = MessageDialog.openQuestion(Display.getDefault().getActiveShell(), "Delete",
		"Do you want to remove?");

	if (openQuestion)
	{
	    IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();

	    Contact deletableContact = view.getSelectedItem();
	    int idNumber = deletableContact.getId();
	    persons.getContacts().remove(deletableContact);

	    setIndex(deletableContact, idNumber);
	    view.refresh();

	    if (AddressBookEditor.contact == deletableContact)
		activePage.closeAllEditors(false);

	    if (!AddressBookDetailsView.elementsSelected.isEmpty())
	    {
		if (AddressBookDetailsView.elementsSelected.contains(deletableContact))
		    AddressBookDetailsView.elementsSelected.remove(deletableContact);
		refresDetailshView();
	    }
	}
    }

    @Override
    public void selectionChanged(IAction action, ISelection selection)
    {
    }

    private void setIndex(Contact contact, int number)
    {
	if (!persons.getContacts().isEmpty())
	{
	    for (Contact element : persons.getContacts())
	    {
		if (element.getId() > number)
		{
		    element.setId(element.getId() - 1);
		    Contact.index = persons.getContacts().get(persons.getContacts().size() - 1).getId() + 1;
		} else
		    Contact.index = contact.getId();
	    }
	} else
	    Contact.index = 1;
    }

    private void refresDetailshView()
    {
	IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
	AddressBookDetailsView addressBookDetailsView = (AddressBookDetailsView) activePage
		.findView(AddressBookDetailsView.ID);

	addressBookDetailsView.refresh();
    }
}