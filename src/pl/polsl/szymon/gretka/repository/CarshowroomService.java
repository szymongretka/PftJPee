package pl.polsl.szymon.gretka.repository;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import pl.polsl.szymon.gretka.entity.CarShowroom;
/**
 *
 * @author Szymek
 */
public class CarshowroomService extends EntityManagerFactoryInit {
    
    private EntityManager em;

    public CarshowroomService() {
        this.em = emf.createEntityManager();
    }
    
    public List<CarShowroom> getAllCarshowrooms() {
        return em.createNamedQuery(CarShowroom.FIND_ALL).getResultList();
    }
    
    public CarShowroom findById(Long id) {
        return em.find(CarShowroom.class, id);
    }
    
    public List<CarShowroom> getCarshowroomsByParameters(String name, 
            String city, String street) {
        return em.createQuery(createQuery(name, city, street)).getResultList();
    }
    
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
    
    public void updateCarShowroom(final CarShowroom givenCarShowroom, 
            final Long id) {
        final CarShowroom carShowroom = em.find(CarShowroom.class, id);
        if(carShowroom != null) {
            carShowroom.setName(givenCarShowroom.getName());
            carShowroom.setStreet(givenCarShowroom.getStreet());
            carShowroom.setCity(givenCarShowroom.getCity());
            try {
                em.getTransaction().begin();
                em.merge(carShowroom);
                em.getTransaction().commit();
            }
            finally {
                if (em.getTransaction().isActive())
                    em.getTransaction().rollback();
            }
        }
    }
    
    public void deleteCarShowroom(final Long id) {
        CarShowroom carShowroom = em.find(CarShowroom.class, id);
        if(carShowroom != null)
            em.remove(carShowroom);
        else
            System.out.println("Carshowroom with given id not found!");
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
    
}
