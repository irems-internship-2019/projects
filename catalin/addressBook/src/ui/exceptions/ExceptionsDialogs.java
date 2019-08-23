package ui.exceptions;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;

public class ExceptionsDialogs extends Exception
{
    private static final long serialVersionUID = 1L;

    public ExceptionsDialogs()
    {
	    MessageDialog.openError(Display.getDefault().getActiveShell(), "Error", "No connection to the server");
    }
}
