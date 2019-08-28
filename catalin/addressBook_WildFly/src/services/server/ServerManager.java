package services.server;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import ui.exceptions.ExceptionsDialogs;

public class ServerManager
{
    private static final String PERSISTENCE_UNIT_NAME = "contacts";
    
    public EntityManager setConnection() throws ExceptionsDialogs
    {
	final EntityManagerFactory factory;
	factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	EntityManager entityManager = factory.createEntityManager();

	return entityManager;
}
}