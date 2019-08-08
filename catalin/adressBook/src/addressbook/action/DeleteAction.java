package addressbook.action;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;

import addressbook.persons.Contact;
import addressbook.persons.Contact.ContactElements;
import addressbook.view.AddressBookView;

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
		boolean openQuestion = MessageDialog.openQuestion(Display.getDefault().getActiveShell(), "Delete", "Do you want to remove?");
		
		if (openQuestion)
		{
			Contact deletableContact = view.getSelectedItem();
			ContactElements.INSTANCE.getContacts().remove(deletableContact);
			
			view.refresh();
		}
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) 
	{

	}
}