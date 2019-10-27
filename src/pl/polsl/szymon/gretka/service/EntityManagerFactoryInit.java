package pl.polsl.szymon.gretka.service;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Szymon Gretka
 */
public class EntityManagerFactoryInit {
    
    /**
     * Initialization of the entityManagerFactory
     */
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("JpaPU");

    /**
     * Closes the entity manager factory
     */
    public void closeManagerFactory() {
        emf.close();
    }
    
}
