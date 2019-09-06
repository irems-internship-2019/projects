package ui.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;

import ui.editor.AddressBookEditor;

public class AddAction implements IViewActionDelegate
{

    @Override
    public void init(IViewPart view)
    {
    }

    @Override
    public void run(IAction action)
    {
	boolean openQuestion = MessageDialog.openQuestion(Display.getDefault().getActiveShell(), "Add",
		"Do you want to add?");
	if (openQuestion)
	{
	    AddressBookEditor.openEditor(null);
	}
    }

    @Override
    public void selectionChanged(IAction action, ISelection selection)
    {
	// TODO Auto-generated method stub

    }
}
