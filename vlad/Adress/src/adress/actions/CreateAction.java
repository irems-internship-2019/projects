package adress.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;

import Model.Address;
import Model.Contact;
import address.AddressContactsView;
import adress.editor.AddressBookEditor;

public class CreateAction implements IViewActionDelegate {
   
	@Override
	public void run(IAction action) {
		// TODO Auto-generated method stub
		boolean openQuestion = MessageDialog.openQuestion(Display.getDefault().getActiveShell(), "Create",
				"Do you want to create a new Contact?");
			if (openQuestion)
			{
			    AddressBookEditor.openEditor(null);
			}	
		
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(IViewPart view) {
		// TODO Auto-generated method stub
	
	}

}
