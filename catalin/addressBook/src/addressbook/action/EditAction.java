package addressbook.action;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;

import addressbook.editor.AddressBookEditor;
import addressbook.view.AddressBookView;

public class EditAction implements IViewActionDelegate
{
    private AddressBookView view;

    @Override
    public void init(IViewPart view)
    {
	this.view = (AddressBookView) view;
    }

    @Override
    public void run(IAction action)
    {
	boolean openQuestion = MessageDialog.openQuestion(Display.getDefault().getActiveShell(), "Edit",
		"Do you want to edit?");

	if (openQuestion)
	{
	    AddressBookEditor.openEditor(view.getSelectedItem());
	}
    }

    @Override
    public void selectionChanged(IAction action, ISelection selection)
    {
	// TODO Auto-generated method stub
    }
}