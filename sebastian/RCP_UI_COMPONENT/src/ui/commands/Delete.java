package ui.commands;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;

import ejb_Handler.DatabaseHandler;
import model.contacts.ContactsManager;
//import model.contacts.ContactsModel;
//import services.database.DatabaseServices;
import services.exceptions.MyCustomException;
import ui.editor.EditorView;
import ui.views.ContactsView;
import ui.views.DetailesView;

public class Delete implements IViewActionDelegate
{
   //ContactsModel contact = new ContactsModel();
    // ViewerTools vTools = new ViewerTools();
    private ContactsView view;
    private static ContactsManager earlyContac;
    //private DatabaseServices dbs = new DatabaseServices();
    private DatabaseHandler hand = new DatabaseHandler(); 
    //private CreateDatabaseHashMap map = new CreateDatabaseHashMap();

    @Override
    public void init(IViewPart view)
    {
	this.view = (ContactsView) view;
    }

    public void earlySelection(ContactsManager earlyContact)
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
		try
		{
		    hand.GetBean().deleteDatabaseContact(deletableContact.getLongID());
		} catch (MyCustomException e)
		{
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
		
		EditorView addressBookEditor = (EditorView) activePage.getActiveEditor();
		if (addressBookEditor != null)
		    EditorView.openEditor(null);

		if (activePage.findView(DetailesView.ID) != null && deletableContact.equals(earlyContac))
		{
		    activePage.hideView(activePage.findView(DetailesView.ID));
		}
		
	    } else
		MessageDialog.openError(Display.getDefault().getActiveShell(), "Delete Contact", "Nothing selected");

	    view.refreshView();
	}
    }

    @Override
    public void selectionChanged(IAction action, ISelection selection)
    {
    }
}
