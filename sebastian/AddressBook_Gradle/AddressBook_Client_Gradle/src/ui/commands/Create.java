package ui.commands;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;

import ui.editor.EditorView;

public class Create implements IViewActionDelegate
{

    @Override
    public void init(IViewPart view)
    {

    }

    @Override
    public void run(IAction action)
    {
	    EditorView.openEditor(null);
    }

    @Override
    public void selectionChanged(IAction action, ISelection selection)
    {
	// TODO Auto-generated method stub

    }

}
