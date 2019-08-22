package ui.exceptions;

import java.sql.SQLException;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;

public class ExceptionsDialogs extends Exception
{
    private static final long serialVersionUID = 1L;

    public ExceptionsDialogs(Object exception)
    {
	int number = 0;
	if (exception instanceof Exception || exception instanceof SQLException)
	    number = 1;
	if(number == 1)
	    MessageDialog.openError(Display.getDefault().getActiveShell(), "Error", "No connection to the server");
    }
}
