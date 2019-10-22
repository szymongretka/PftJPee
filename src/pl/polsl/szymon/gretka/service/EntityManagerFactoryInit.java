package pl.polsl.szymon.gretka.service;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Szymek
 */
public class EntityManagerFactoryInit {
    
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("JpaPU");

}
