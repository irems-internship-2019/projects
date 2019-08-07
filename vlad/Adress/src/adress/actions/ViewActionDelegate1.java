package adress.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;

import adress.View;
import adress.editor.AdressBookEditor;
import checker.SelectChecker;

public class ViewActionDelegate1 implements IViewActionDelegate {

	private View view;
	
	
	@Override
	public void run(IAction action) {
		// TODO Auto-generated method stub

		AdressBookEditor.openEditor(view.getSelectedItem());
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(IViewPart view) {
		// TODO Auto-generated method stub
		this.view = (View) view;
	}

}
