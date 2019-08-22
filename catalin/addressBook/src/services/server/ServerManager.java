package services.server;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

import ui.exceptions.ExceptionsDialogs;

public class ServerManager
{
    public Statement setConnection() throws ExceptionsDialogs
    {
	try
	{   FileInputStream files=new FileInputStream("C:\\Users\\Fujitsu\\Documents\\git\\projects\\catalin\\addressBook\\src\\services\\server\\connection.properties"); 
            Properties properties=new Properties (); 
            properties.load (files); 
            String dname= (String) properties.get ("Dname"); 
            String url= (String) properties.get ("URL"); 
            String username= (String) properties.get ("Uname"); 
            String password= (String) properties.get ("password"); 
            Class.forName(dname);    
            
	    Connection connection = DriverManager.getConnection(url, username, password);
	    Statement statement = connection.createStatement();
	    return statement;
	} catch (Exception e)
	{
	   throw new ExceptionsDialogs(e);
	}
    }
}