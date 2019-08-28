package services.server;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class ServerManager
{
    private static final String PERSISTENCE_UNIT_NAME = "contacts";
    
    public EntityManager setConnection()
    {
	final EntityManagerFactory factory;
	factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	EntityManager entityManager = factory.createEntityManager();

	return entityManager;
}
}