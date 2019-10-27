package pl.polsl.szymon.gretka.service;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import pl.polsl.szymon.gretka.entity.CarShowroom;
import pl.polsl.szymon.gretka.exception.EntityNotFoundException;
/**
 * Bussines logic for the carshowroom
 * 
 * @author Szymon Gretka
 */
public class CarshowroomService extends EntityManagerFactoryInit {
    
    /**
     * Initialization of the entityManager
     */
    private EntityManager em;

    /**
     * No args constructor
     */
    public CarshowroomService() {
        this.em = emf.createEntityManager();
    }
    
    /**
     * Returns list of all carshowrooms
     * @return list
     */
    public List<CarShowroom> getAllCarshowrooms() {
        return em.createNamedQuery(CarShowroom.FIND_ALL).getResultList();
    }
    
    /**
     * Finds carshowroom by its id
     * 
     * @param id
     * @return car
     * @throws EntityNotFoundException thrown if none object matches given id
     */
    public CarShowroom findById(Long id) throws EntityNotFoundException {
        CarShowroom carShowroom = em.find(CarShowroom.class, id);
        if(carShowroom != null)
            return carShowroom;
        else
            throw new EntityNotFoundException();
    }
    
    /**
     * Returns list of carshowrooms which fulfill given parameters
     * 
     * @param name
     * @param city
     * @param street
     * @return
     */
    public List<CarShowroom> getCarshowroomsByParameters(String name, 
            String city, String street) {
        return em.createQuery(createQuery(name, city, street)).getResultList();
    }
    
    /**
     * Creates a new carshowroom
     * 
     * @param carShowroom
     */
    public void createCarShowroom(final CarShowroom carShowroom) {
        try {
            em.getTransaction().begin();
            em.persist(carShowroom);
            em.getTransaction().commit();
        }
        finally {
            if (em.getTransaction().isActive())
                em.getTransaction().rollback();
        }
    }
    
    /**
     * Updates the carshowroom
     * 
     * @param givenCarShowroom
     * @param id
     */
    public void updateCarShowroom(final CarShowroom givenCarShowroom, 
            final Long id) {
        final CarShowroom carShowroom = em.find(CarShowroom.class, id);
        if(carShowroom != null) {
            carShowroom.setName(givenCarShowroom.getName());
            carShowroom.setStreet(givenCarShowroom.getStreet());
            carShowroom.setCity(givenCarShowroom.getCity());
            carShowroom.setCars(givenCarShowroom.getCars());
            try {
                em.getTransaction().begin();
                em.merge(carShowroom);
                em.getTransaction().commit();
            }
            finally {
                if (em.getTransaction().isActive())
                    em.getTransaction().rollback();
                em.clear();
            }
        }
    }
    
    /**
     * Deletes the carshowroom with given id
     * 
     * @param id
     * @throws EntityNotFoundException thrown if none object matches given id
     */
    public void deleteCarShowroom(final Long id) throws EntityNotFoundException {
        CarShowroom carShowroom = em.find(CarShowroom.class, id);
        if(carShowroom != null) {
            em.getTransaction().begin();
            em.remove(carShowroom);
            em.getTransaction().commit();
        }
        else
            throw new EntityNotFoundException();
    }
    
    
    
    private CriteriaQuery<CarShowroom> createQuery(String name, String city, 
            String street){
        
        Expression expr;
        Root<CarShowroom> queryRoot;
        CriteriaQuery<CarShowroom> queryDefinition;
        List<Predicate> predicates = new ArrayList<>();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        
        queryDefinition = builder.createQuery(CarShowroom.class);
        queryRoot = queryDefinition.from(CarShowroom.class);
        queryDefinition.select(queryRoot);
        if(name != null){
            expr = queryRoot.get("name");
            predicates.add(builder.like(expr, name));
        }
        if(city != null){
            expr = queryRoot.get("city");
            predicates.add(builder.like(expr, city));
        }
        if(street != null){
            expr = queryRoot.get("street");
            predicates.add(builder.like(expr, street));
        }
        if(!predicates.isEmpty()) {
            queryDefinition.where(
                    builder.and(predicates.toArray(
                        new Predicate[predicates.size()])));
        }
        return queryDefinition;
    }
    
    /**
     * Closes the entityManager
     */
    public void closeEntityManager() {
        em.clear();
        em.close();
    }
    
}
