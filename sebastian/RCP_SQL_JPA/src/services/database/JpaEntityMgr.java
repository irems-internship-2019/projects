package services.database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.enums.ErrorsEnum;
import ui.exceptions.MyCustomException;

public class JpaEntityMgr
{

    public EntityManager EntityMgr() throws MyCustomException
    {
	EntityManager em;
	try
	{
	    String PERSISTENCE_UNIT_NAME = "AddressBook";
	    EntityManagerFactory factory;

	    factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	    em = factory.createEntityManager();

	} catch (Exception e)
	{
	    throw new MyCustomException(e, ErrorsEnum.CONNECTION);

	}
	return em;
	
    }
}