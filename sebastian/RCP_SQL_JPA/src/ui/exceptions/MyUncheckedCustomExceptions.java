package ui.exceptions;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;

import model.enums.ErrorsEnum;

public class MyUncheckedCustomExceptions extends RuntimeException
{

    private static final long serialVersionUID = -8718828512143293558L;

    public MyUncheckedCustomExceptions(Throwable error, ErrorsEnum code)
    {

	if (code.equals(ErrorsEnum.BAD))
	{
	    MessageDialog.openError(Display.getDefault().getActiveShell(), "ERROR",
		    "Something bad happened, check StackTrace pls");
	    error.printStackTrace();
	} else
	{
	    MessageDialog.openError(Display.getDefault().getActiveShell(), "ERROR",
		    "U messed up rly hard, fam");
	    error.printStackTrace();
	}
    }
}
