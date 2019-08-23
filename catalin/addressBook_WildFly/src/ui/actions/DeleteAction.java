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
import services.server.ServerServices;
import ui.editor.AddressBookEditor;
import ui.exceptions.ExceptionsDialogs;
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
	    try
	    {
		manager.deleteServerContact(deletableContact);
		view.refresh();
	    } catch (ExceptionsDialogs e)
	    {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }

	    if (editor != null && editor.getModel() != null)
	    {
		if(editor.getModel().getId() == deletableContact.getId())
		activePage.setEditorAreaVisible(false);
	    }

	    if (addressBookDetailsView != null && addressBookDetailsView.getDetailsViewContact().get(0).getId() == deletableContact.getId())
		activePage.hideView(addressBookDetailsView);
	}
    }

    @Override
    public void selectionChanged(IAction action, ISelection selection)
    {
    }
}