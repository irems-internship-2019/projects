package dataBaseManager;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DataBaseManager {

	private static final String PERSISTENCE_UNIT_NAME = "contacts";
	
	 public EntityManager enableConnection() 
	    {
		final EntityManagerFactory factory;
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager entityManager = factory.createEntityManager();

		return entityManager;
	    }
}
