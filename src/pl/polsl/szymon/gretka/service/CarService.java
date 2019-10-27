package pl.polsl.szymon.gretka.service;


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

/**
 * Bussines logic for the car
 * 
 * @author Szymon Gretka
 */
public class CarService extends EntityManagerFactoryInit {
    
    /**
     * Initialization of the entityManager
     */
    EntityManager em;

    /**
     * No args constructor
     */
    public CarService() {
        this.em = emf.createEntityManager();
    }
    
    /**
     * Returns list of all carshowrooms
     * @return list
     */
    public List<Car> getAllCars() {
        return em.createNamedQuery(Car.FIND_ALL).getResultList();
    }
    
    /**
     * Finds car by its id
     * 
     * @param id
     * @return car
     * @throws EntityNotFoundException thrown if none object matches given id
     */
    public Car findById(Long id) throws EntityNotFoundException {
        Car car = em.find(Car.class, id);
        if(car != null)
            return car;
        else
            throw new EntityNotFoundException();
    }
    
    /**
     * Returns list of cars which fulfill given parameters
     * 
     * @param brand
     * @param model
     * @param colour
     * @param year
     * @return
     */
    public List<Car> getCarByParameters(String brand, String model, 
            String colour, Integer year) {
        return em.createQuery(createQuery(brand, model, colour, year)).getResultList();
    }
    
    /**
     * Creates a new car
     * 
     * @param car
     */
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
    
    /**
     * Updates the car with given id
     * 
     * @param givenCar
     * @param id
     */
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
    
    /**
     * Deletes the car with given id
     * 
     * @param id
     * @throws EntityNotFoundException thrown if none object matches given id
     */
    public void deleteCar(final Long id) throws EntityNotFoundException {
        Car car = em.find(Car.class, id);
        if(car != null) {
            em.getTransaction().begin();       
            em.remove(car);
            em.getTransaction().commit();
        }
        else
            throw new EntityNotFoundException();
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
    
    /**
     * Closes the entityManager
     */
    public void closeEntityManager() {
        em.clear();
        em.close();
    }
    
}
