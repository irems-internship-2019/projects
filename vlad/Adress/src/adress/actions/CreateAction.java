package adress.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
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
		
		AddressBookEditor.openEditor(null);
		
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
