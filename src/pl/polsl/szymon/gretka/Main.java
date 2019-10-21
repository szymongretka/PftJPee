package pl.polsl.szymon.gretka;

import pl.polsl.szymon.gretka.controller.CarController;
import pl.polsl.szymon.gretka.controller.CarshowroomController;
import pl.polsl.szymon.gretka.exception.EntityNotFoundException;
import pl.polsl.szymon.gretka.repository.CarService;
import pl.polsl.szymon.gretka.repository.CarshowroomService;
import pl.polsl.szymon.gretka.view.View;

/**
 *
 * @author Szymek
 */
public class Main {

   
    public static void main(String[] args) throws EntityNotFoundException {
        
        CarshowroomService carshowroomService = new CarshowroomService();
        CarService carService = new CarService();
        View view = new View();
        
        CarController carController = new CarController(carService, view);
        CarshowroomController carshowroomController = new CarshowroomController(carshowroomService, view);

        
        ConsoleMenu menu = new ConsoleMenu(carController, carshowroomController, view);
        menu.execute();
        
        /*System.out.println("carshowroomController getAll: " + carshowroomController.);
        System.out.println("carshowroomController find by id: " + carshowroomService.findById(1L));
        System.out.println("carshowroomController criteria query: " + carshowroomService.getCarshowroomsByParameters("nowy", "tychy", "p%"));
        
        System.out.println("carController getAll: ");
        carController.getAllCars();
        System.out.println("carController find by id: ");
        carController.getCarById(1L);
        System.out.println("carController criteria query: ");
        carController.getCarsByParameters("bmw", "s%", "b%", 2019);*/
        
        
    }
    
}
