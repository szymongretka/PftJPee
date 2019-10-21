package pl.polsl.szymon.gretka.controller;

import java.util.List;
import pl.polsl.szymon.gretka.entity.Car;
import pl.polsl.szymon.gretka.exception.EntityNotFoundException;
import pl.polsl.szymon.gretka.repository.CarService;
import pl.polsl.szymon.gretka.view.View;

/**
 *
 * @author Szymek
 */
public class CarController {
    
    private final CarService carService;
    private final View view;

    public CarController(CarService carService, View view) {
        this.carService = carService;
        this.view = view;
    }
    
    public void getCarById(final Long id){
        try{
            Car car = carService.findById(id);
            view.print("Car: " + car);
        } catch(EntityNotFoundException e) {
            view.print(e.getMessage());
        }
    }
    
    public void getAllCars(){
        List<Car> cars = carService.getAllCars();
        if(!cars.isEmpty())
            view.print("List of cars: " + cars);
        else
            view.print("Empty list");
    }
    
    public void getCarsByParameters(String brand, String model, String colour, 
            Integer year) {
        List<Car> cars = carService.getCarByParameters(brand, model, 
                colour, year);
        if(!cars.isEmpty())
            view.print("List of cars: " + cars);
        else
            view.print("Empty list");
    }
    
    public void createCar(String brand, String model, String colour, 
            Integer year) {
        
        Car car = new Car(brand, model, colour, year);
        carService.createCar(car);
    }
    
    public void deleteCar(final Long id) {
        try{
            carService.deleteCar(id);
        } catch(EntityNotFoundException e) {
            view.print(e.getMessage());
        }
    }
    
    public void updateCar(final Long id) {
        
    }

    
}
