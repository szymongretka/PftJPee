package pl.polsl.szymon.gretka;

import pl.polsl.szymon.gretka.controller.CarController;
import pl.polsl.szymon.gretka.controller.CarshowroomController;
import pl.polsl.szymon.gretka.service.CarService;
import pl.polsl.szymon.gretka.service.CarshowroomService;
import pl.polsl.szymon.gretka.view.View;

/**
 *
 * @author Szymon Gretka
 */
public class Main {

    /**
     * Main class of the program. It initializes controllers and services and calls
     * the method execute() of menu class.
     * 
     * @param args
     */
    public static void main(String[] args) {
  
        CarshowroomService carshowroomService = new CarshowroomService();
        CarService carService = new CarService();
        View view = new View();
               
        CarController carController = new CarController(carService, view);
        CarshowroomController carshowroomController = new CarshowroomController(carshowroomService, view);
       
        ConsoleMenu menu = new ConsoleMenu(carController, carshowroomController, 
                carService, carshowroomService, view);
        menu.execute();

        
    }
    
}
