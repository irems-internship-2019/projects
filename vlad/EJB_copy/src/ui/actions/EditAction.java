package ui.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;

import ui.editors.AddressBookEditor;
import ui.views.AddressContactsView;

public class EditAction implements IViewActionDelegate {

	AddressContactsView view;

	@Override
	public void run(IAction action) {
		// TODO Auto-generated method stub

		AddressBookEditor.openEditor(view.getSelectedItem());
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(IViewPart view) {
		// TODO Auto-generated method stub
		this.view = (AddressContactsView) view;
	}
}
