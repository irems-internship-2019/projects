package services.database;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import model.enums.ErrorsEnum;
import ui.exceptions.MyCustomException;

public class DatabaseConnection
{


    
    public Statement establishConnection() throws MyCustomException
    {

	FileInputStream fis;
	try
	{
	    //System.out.println(System.getProperty("user.dir")); D:\RCPEclipse\eclipse WHY?
	    fis = new FileInputStream("C:\\Users\\Paul\\Documents\\internship\\sebastian\\RCP_SQL\\src\\services\\database\\connection.prop");
	    Properties p = new Properties();
	    p.load(fis);
	    String dname = (String) p.get("Dname");
	    String url = (String) p.get("URL");
	    String username = (String) p.get("Uname");
	    String password = (String) p.get("password");
	    Class.forName(dname);
	    Connection con = DriverManager.getConnection(url, username, password);
	    Statement stmt = con.createStatement();
	    
	    return stmt;
	  

	} catch (Exception e)
	{
	    throw new MyCustomException(e,ErrorsEnum.CONNECTION);

	}

    }

}