package ui.editor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;

public class RegexValidation
{
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
	    Pattern.CASE_INSENSITIVE);

    public static final Pattern VALID_PHONE_NUMBER_REGEX = Pattern
	    .compile("\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}", Pattern.CASE_INSENSITIVE);

    public boolean validateEmail(String emailStr)
    {
	Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);

	if (matcher.find())
	    return true;
	else
	{
	   // MessageDialog.openInformation(Display.getDefault().getActiveShell(), "ERROR", "That's not an email");
	    return false;
	}

    }

    public boolean validatePhoneNumber(String emailStr)
    {
	Matcher matcher = VALID_PHONE_NUMBER_REGEX.matcher(emailStr);

	if (matcher.find())
	    return true;
	else
	{
	    //MessageDialog.openInformation(Display.getDefault().getActiveShell(), "ERROR", "That's a phone number");
	    return false;
	}

    }
}
