package rcpbook.cmd;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;

import rcpbook.view.ContactsView;
import rcpbook.view.EditorView;

public class Edit implements IViewActionDelegate
{

    private ContactsView view;

    @Override
    public void init(IViewPart view)
    {
	this.view = (ContactsView) view;

    }

    @Override
    public void run(IAction action)
    {
	    EditorView.openEditor(view.getSelectedItem());
	
    }

    @Override
    public void selectionChanged(IAction action, ISelection selection)
    {
	// TODO Auto-generated method stub

    }

}
