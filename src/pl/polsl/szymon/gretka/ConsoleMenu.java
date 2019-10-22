package pl.polsl.szymon.gretka;

import pl.polsl.szymon.gretka.controller.CarController;
import pl.polsl.szymon.gretka.controller.CarshowroomController;
import pl.polsl.szymon.gretka.entity.Car;
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

        System.out.println("===========================");
        System.out.println("[1] Create Car");
        System.out.println("[2] Read Car");
        System.out.println("[3] Update Car");
        System.out.println("[4] Delete Car");
        System.out.println("===========================");
        System.out.println("[5] Create CarShowroom");
        System.out.println("[6] Read CarShowroom");
        System.out.println("[7] Update CarShowroom");
        System.out.println("[8] Delete CarShowroom");
        System.out.println("===========================");
        System.out.println("[9] Exit");
        System.out.println("===========================");

        int selection = view.readInt();

        while (selection != 9) {

            switch (selection) {
                case 1:
                    createOperation();
                    break;
                case 2:
                    readOperation();
                    break;
                case 3:
                    updateOperation();
                    break;
                case 4:
                    deleteOperation();
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
                    selection = 9;
                    break;

            }

        }

    }
    

    private void createOperation() {
        Car car = getCreatedCar();
        carController.createCar(car);
        execute();
    }

    private void updateOperation() {
        System.out.println("Insert ID of the car which would you like to update: ");
        Long id = view.readLong();
        Car updatedCar = getCreatedCar();
        carController.updateCar(id, updatedCar);

    }
    
    private void deleteOperation() {
        System.out.println("Insert ID of the car which would you like to delete: ");
        Long id = view.readLong();
        carController.deleteCar(id);
    }


    private void readOperation() {
        System.out.println("Which option would you like to perform?");
        System.out.println("[1] Find Car by ID");
        System.out.println("[2] Find all Cars");
        System.out.println("[3] Find all Cars which are matching parameters");
        System.out.println("[4] Go back");
        int choice = view.readInt();
        switch (choice) {
            case 1:
                findById();
                break;
            case 2:
                carController.getAllCars();
                break;
            case 3:
                findByParameters();
                break;
            case 4:
                execute();
                break;
            default:
                break;
        }
    }
    
    private void findById() {
        Long id = view.readLong();
        carController.getCarById(id);
    }

    private void findByParameters() {
        System.out.println("Insert parameters:");
        System.out.println("Brand: ");
        String brand = view.readString();
        System.out.println("model: ");
        String model = view.readString();
        System.out.println("colour: ");
        String colour = view.readString();  
        System.out.println("year: ");
        Integer year = view.readInt();
        carController.getCarsByParameters(brand, model, colour, year);
        execute();
    }
    
    private Car getCreatedCar(){
        System.out.println("Insert parameters:");
        System.out.println("Brand: ");
        String brand = view.readString();
        System.out.println("model: ");
        String model = view.readString();
        System.out.println("colour: ");
        String colour = view.readString();  
        System.out.println("year: ");
        Integer year = view.readInt();
        return new Car(brand, model, colour, year);
    }

}
