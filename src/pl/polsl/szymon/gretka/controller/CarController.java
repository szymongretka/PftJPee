package pl.polsl.szymon.gretka.controller;

import java.util.List;
import pl.polsl.szymon.gretka.entity.Car;
import pl.polsl.szymon.gretka.exception.EntityNotFoundException;
import pl.polsl.szymon.gretka.service.CarService;
import pl.polsl.szymon.gretka.view.View;

/**
 * Car controller which encapsulates all data and returns the view
 * 
 * @author Szymon Gretka
 */
public class CarController {
    
    
    private final CarService carService;
    private final View view;

    /**
     * Constructor for creating a new carcontroller.
     * @param carService
     * @param view
     */
    public CarController(CarService carService, View view) {
        this.carService = carService;
        this.view = view;
    }
    
    /**
     * Returns the view of the car with given id 
     * or throws exception if none found
     * @param id
     */
    public void getCarById(final Long id){
        try{
            Car car = carService.findById(id);
            view.print("Car: " + car);
        } catch(EntityNotFoundException e) {
            view.print(e.getMessage());
        }
    }
    
    /**
     * Returns list of all cars
     */
    public void getAllCars(){
        List<Car> cars = carService.getAllCars();
        if(!cars.isEmpty())
            view.print("List of cars: " + cars);
        else
            view.print("Empty list");
    }
    
    /**
     * Returns list of cars which fulfill given parameters
     * @param brand
     * @param model
     * @param colour
     * @param year
     */
    public void getCarsByParameters(String brand, String model, String colour, 
            Integer year) {
        List<Car> cars = carService.getCarByParameters(brand, model, 
                colour, year);
        if(!cars.isEmpty())
            view.print("List of cars: " + cars);
        else
            view.print("Empty list");
    }
    
    /**
     * Creates a new car
     * @param car
     */
    public void createCar(Car car) {
        carService.createCar(car);
    }
    
    /**
     * Deletes the car with given id
     * @param id
     */
    public void deleteCar(final Long id) {
        try {
            carService.deleteCar(id);
        } catch(EntityNotFoundException e) {
            view.print(e.getMessage());
        }
    }
    
    /**
     * Updated the car with given id
     * @param id
     * @param updatedCar
     */
    public void updateCar(final Long id, Car updatedCar) {
        carService.updateCar(updatedCar, id);
    }

    
}
