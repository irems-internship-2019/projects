package ui.actions;

import java.util.Properties;

import javax.naming.InitialContext;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;

import addressejb.Services;
import addressejb.ServicesRemote;
import models.persons.Contact;
import ui.editor.AddressBookEditor;
import ui.views.AddressBookDetailsView;
import ui.views.AddressBookView;

public class DeleteAction implements IViewActionDelegate
{
    private AddressBookView view;

    private ServicesRemote createBean()
    {
	ServicesRemote bean = null;
	try{   
	Properties props = new Properties();
        props.put("java.naming.factory.url.pkgs","org.jboss.ejb.client.naming");
        InitialContext context = new InitialContext(props);

        String appName = "";        	 
        String moduleName = "EJBAddressBook";
        String distinctName = "";        	 
        String beanName = Services.class.getSimpleName();        	 
        String interfaceName = ServicesRemote.class.getName();
        String name = "ejb:" + appName + "/" + moduleName + "/" +  distinctName    + "/" + beanName + "!" + interfaceName;
        bean = (ServicesRemote)context.lookup(name);
	}catch(Exception e){
		e.printStackTrace();
	}
	return bean;
    }
    
    @Override
    public void init(IViewPart view)
    {
	this.view = (AddressBookView) view;
    }

    @Override
    public void run(IAction action)
    {
	ServicesRemote beanElement = createBean();
	
	boolean openQuestion = MessageDialog.openQuestion(Display.getDefault().getActiveShell(), "Delete",
		"Do you want to remove?");

	if (openQuestion)
	{   
	    IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
	    AddressBookEditor editor = (AddressBookEditor) activePage.getActiveEditor();
	    AddressBookDetailsView addressBookDetailsView = (AddressBookDetailsView) activePage
		    .findView(AddressBookDetailsView.ID);

	    Contact deletableContact = view.getSelectedItem(); 
	    beanElement.deleteServerContact(deletableContact);
	    view.refresh();

	    if (editor != null && editor.getModel() != null)
	    {
		if(editor.getModel().getContactid() == deletableContact.getContactid())
		activePage.setEditorAreaVisible(false);
	    }

	    if (addressBookDetailsView != null && addressBookDetailsView.getDetailsViewContact().get(0).getContactid() == deletableContact.getContactid())
		activePage.hideView(addressBookDetailsView);
	}
    }

    @Override
    public void selectionChanged(IAction action, ISelection selection)
    {
    }
}