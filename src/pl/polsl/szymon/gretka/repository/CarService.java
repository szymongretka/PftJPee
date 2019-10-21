package pl.polsl.szymon.gretka.repository;


import java.util.ArrayList;
import java.util.List;
import pl.polsl.szymon.gretka.entity.Car;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import pl.polsl.szymon.gretka.exception.EntityNotFoundException;
import pl.polsl.szymon.gretka.exception.SimpleException;

/**
 *
 * @author Szymek
 */
public class CarService extends EntityManagerFactoryInit {
    
    EntityManager em;

    public CarService() {
        this.em = emf.createEntityManager();
    }
    
    public List<Car> getAllCars() {
        return em.createNamedQuery(Car.FIND_ALL).getResultList();
    }
    
    public Car findById(Long id) throws EntityNotFoundException {
        Car car = em.find(Car.class, id);
        if(car != null)
            return car;
        else
            throw new EntityNotFoundException();
    }
    
    public List<Car> getCarByParameters(String brand, String model, 
            String colour, Integer year) {
        return em.createQuery(createQuery(brand, model, colour, year)).getResultList();
    }
    
    public void createCar(final Car car) {
        try {
            em.getTransaction().begin();
            em.persist(car);
            em.getTransaction().commit();
        }
        finally {
            if (em.getTransaction().isActive())
                em.getTransaction().rollback();
        }
    }
    
    public void updateCar(final Car givenCar, final Long id) {
        final Car car = em.find(Car.class, id);
        if(car != null) {
            car.setBrand(givenCar.getBrand());
            car.setColour(givenCar.getColour());
            car.setModel(givenCar.getModel());
            car.setYear(givenCar.getYear());
            try {
                em.getTransaction().begin();
                em.merge(car);
                em.getTransaction().commit();
            }
            finally {
                if (em.getTransaction().isActive())
                    em.getTransaction().rollback();
            }
        }
    }
    
    public void deleteCar(final Long id) throws EntityNotFoundException {
        Car car = em.find(Car.class, id);
        if(car != null)
            em.remove(car);
        else
            throw new EntityNotFoundException();
            //System.out.println("Car with given id not found!");
    }
    
    
    private CriteriaQuery<Car> createQuery(String brand, String model, 
            String colour, Integer year) {
        
        Expression expr;
        Root<Car> queryRoot;
        CriteriaQuery<Car> queryDefinition;
        List<Predicate> predicates = new ArrayList<>();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        
        queryDefinition = builder.createQuery(Car.class);
        queryRoot = queryDefinition.from(Car.class);
        queryDefinition.select(queryRoot);
        if(brand != null){
            expr = queryRoot.get("brand");
            predicates.add(builder.like(expr, brand));
        }
        if(model != null){
            expr = queryRoot.get("model");
            predicates.add(builder.like(expr, model));
        }
        if(colour != null){
            expr = queryRoot.get("colour");
            predicates.add(builder.like(expr, colour));
        }
        if(year != null){
            expr = queryRoot.get("year");
            predicates.add(builder.greaterThanOrEqualTo(expr, year));
        }
        if(!predicates.isEmpty()) {
            queryDefinition.where(
                    builder.and(predicates.toArray(
                        new Predicate[predicates.size()])));
        }
        return queryDefinition;
    }
    
}
