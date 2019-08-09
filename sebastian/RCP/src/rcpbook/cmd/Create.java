package rcpbook.cmd;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;

import rcpbook.view.EditorView;

public class Create implements IViewActionDelegate {

	@Override
	public void init(IViewPart view) {

	}

	@Override
	public void run(IAction action) {

		EditorView.openEditor(null);

	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub

	}

}
