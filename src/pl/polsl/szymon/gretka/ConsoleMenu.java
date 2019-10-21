package pl.polsl.szymon.gretka;

import pl.polsl.szymon.gretka.controller.CarController;
import pl.polsl.szymon.gretka.controller.CarshowroomController;
import pl.polsl.szymon.gretka.view.View;

/**
 *
 * @author Szymek
 */
class ConsoleMenu {

    private final CarController carController;
    private final CarshowroomController carshowroomController;
    private final View view;

    public ConsoleMenu(CarController carController, CarshowroomController carshowroomController, View view) {
        this.carController = carController;
        this.carshowroomController = carshowroomController;
        this.view = view;
    }

    public void execute() {

        System.out.println("[1] Create Car");
        System.out.println("[2] Read Car");
        System.out.println("[3] Update Car");
        System.out.println("[4] Delete Car");
        System.out.println("[5] Create CarShowroom");
        System.out.println("[6] Read CarShowroom");
        System.out.println("[7] Update CarShowroom");
        System.out.println("[8] Delete CarShowroom");
        System.out.println("[9] Exit");

        int selection = view.readInt();

        
        while (selection != 9) {
        outer:    
            switch (selection) {

                case 1:
                    //carController.createCar(brand, model, colour, selection);
                    break;
                case 2:
                    System.out.println("Which option would you like to perform?");
                    System.out.println("[1] Find Car by ID");
                    System.out.println("[2] Find all Cars");
                    System.out.println("[3] Find all Cars which are matching parameters");
                    System.out.println("[4] Go back");
                    int nestedSelection = view.readInt();

                    switch (nestedSelection) {
                        case 1:
                            Long id = view.readLong();
                            carController.getCarById(id);
                            break;
                        case 2:
                            carController.getAllCars();
                            break;
                        case 3:
                            System.out.println("Insert parameters: ");
                            System.out.print("Brand: ");
                            String brand = view.readString() + "\n";
                            System.out.print("model: ");
                            String model = view.readString() + "\n";
                            System.out.print("colour: ");
                            String colour = view.readString() + "\n";
                            System.out.println("year: ");
                            Integer year = view.readInt();
                            carController.getCarsByParameters(brand, model, colour, year);
                            break;
                        case 4:
                            break outer;
                        default:
                            break;
                    }
                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:

                    break;

                case 6:

                    break;

                case 7:

                    break;

                case 8:

                    break;
                case 9:
                    System.exit(1);
                    break;
            }

        }

    }

  

}
