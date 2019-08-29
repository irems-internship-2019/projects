package services.database;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.enums.ErrorsEnum;
import services.exceptions.MyCustomException;

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