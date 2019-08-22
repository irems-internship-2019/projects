package services.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ServerManager
{
    public Statement setConnection()
    {
	try
	{
	    Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:54323/postgres", "postgres", "1234");
	    Statement statement = connection.createStatement();
	    return statement;
	} catch (SQLException e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return null;
    }
}