package services.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;

public class DatabaseConnection
{
    public Statement establishConnection()
    {
	try
	{
	    Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "sebi");
	    Statement statement = connection.createStatement();
	    return statement;
	} catch (SQLException e)
	{
	    MessageDialog.openInformation(Display.getDefault().getActiveShell(), "ERROR",
		    "Could not establish connection");
	    e.printStackTrace();
	    return null;
	}
    }
}
