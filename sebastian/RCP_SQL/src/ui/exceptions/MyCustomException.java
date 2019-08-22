package ui.exceptions;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;

import model.enums.ErrorsEnum;

public class MyCustomException extends Exception
{

    private static final long serialVersionUID = 7718828512143293558L;

    public MyCustomException(Throwable error, ErrorsEnum code)
    {
	String errorMessage = null;

	switch (code)
	    {
	    case CONNECTION:
		errorMessage = "Could not establish connection";
		break;
	    case INSERT:
		errorMessage = "Insert Query Failed";
		break;
	    case UPDATE:
		errorMessage = "Update Query Failed";
		break;
	    case DELETE:
		errorMessage = "Delete Query Failed";
		break;
	    case LOAD:
		errorMessage = "Failed to load from database";
		break;
	    default:
		errorMessage = "Unimplemented exception";
		break;
	    }

	MessageDialog.openError(Display.getDefault().getActiveShell(), "ERROR", errorMessage);
	error.printStackTrace();
    }
}
