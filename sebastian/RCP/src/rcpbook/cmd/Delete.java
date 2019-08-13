package rcpbook.cmd;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;

import rcpbook.contacts.ContactsManager;
import rcpbook.contacts.ContactsModel;
import rcpbook.view.ContactsView;
import rcpbook.view.DetailesView;
import rcpbook.view.EditorView;

public class Delete implements IViewActionDelegate
{
    ContactsModel contact = new ContactsModel();
    //ViewerTools vTools = new ViewerTools();
    private ContactsView view;
    private static ContactsManager earlyContac;

    @Override
    public void init(IViewPart view)
    {
	this.view = (ContactsView) view;
    }
    
    public void earlySelection( ContactsManager earlyContact) 
    {
	earlyContac = earlyContact;
    }

    @Override 
    public void run(IAction action)
    {
	boolean delete = MessageDialog.openQuestion(Display.getDefault().getActiveShell(), "Delete Contact",
		"Are you shure you want to delete the selected contact?");
	IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
	
	if (delete)
	{
	    ContactsManager deletableContact = view.getSelectedItem();
	    if (deletableContact != null)
	    {
		contact.getElements().remove(contact.getElements().get(deletableContact.getIntId() - 1));
		contact.rearrangeContactsArray();
		EditorView.openEditor(null);
		if(activePage.findView(DetailesView.ID) != null && deletableContact.equals(earlyContac)) 
		{
		    activePage.hideView(activePage.findView(DetailesView.ID));
		}
		
	    } else
		MessageDialog.openError(Display.getDefault().getActiveShell(), "Delete Contact", "Nothing selected");

	    // contact.rearrangeContactsArray();
	   view.refreshView();
	}
    }

    @Override
    public void selectionChanged(IAction action, ISelection selection)
    {
    }
}
