package adress.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;

import Model.Address;
import Model.Contact;
import adress.View;
import adress.editor.AdressBookEditor;

public class ViewActionDelegate3 implements IViewActionDelegate {
   
	@Override
	public void run(IAction action) {
		// TODO Auto-generated method stub
		
		AdressBookEditor.openEditor(null);
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
