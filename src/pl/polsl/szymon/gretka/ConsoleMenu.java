package pl.polsl.szymon.gretka;

import pl.polsl.szymon.gretka.controller.CarController;
import pl.polsl.szymon.gretka.controller.CarshowroomController;
import pl.polsl.szymon.gretka.entity.Car;
import pl.polsl.szymon.gretka.entity.CarShowroom;
import pl.polsl.szymon.gretka.view.View;

/**
 *
 * @author Szymon Gretka
 * @version 1.0
 */
class ConsoleMenu {

    /**
     *
     */
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
                    createOperation(Type.CAR);
                    break;
                case 2:
                    readOperation(Type.CAR);
                    break;
                case 3:
                    updateOperation(Type.CAR);
                    break;
                case 4:
                    deleteOperation(Type.CAR);
                    break;
                case 5:
                    createOperation(Type.CARSHOWROOM);
                    break;
                case 6:
                    readOperation(Type.CARSHOWROOM);
                    break;
                case 7:
                    updateOperation(Type.CARSHOWROOM);
                    break;
                case 8:
                    deleteOperation(Type.CARSHOWROOM);
                    break;
                case 9:
                    System.exit(1);
                    break;
            }

        }

    }

    private void createOperation(Type type) {
        if (type == Type.CAR) {
            Car car = getCreatedCar();
            carController.createCar(car);
        } else if (type == Type.CARSHOWROOM) {
            CarShowroom carShowroom = getCreatedCarShoowroom();
            carshowroomController.createCarShowroom(carShowroom);
        }
        execute();
    }

    private void updateOperation(Type type) {
        System.out.println("Insert ID of the " + type.name() + " which would you like to update: ");
        Long id = view.readLong();
        if (type == Type.CAR) {
            Car updatedCar = getCreatedCar();
            carController.updateCar(id, updatedCar);
        } else if (type == Type.CARSHOWROOM) {
            CarShowroom updatedCarShowroom = getCreatedCarShoowroom();
            carshowroomController.updateCarShowroom(id, updatedCarShowroom);
        }
        execute();
    }

    private void deleteOperation(Type type) {
        System.out.println("Insert ID of the " + type.name() + " which would you like to delete: ");
        Long id = view.readLong();
        if (type == Type.CAR) {
            carController.deleteCar(id);
        } else if (type == Type.CARSHOWROOM) {
            carshowroomController.deleteCarShowroom(id);
        }
        execute();
    }

    private void readOperation(Type type) {
        System.out.println("Which option would you like to perform?");
        System.out.println("[1] Find " + type.name() + " by ID");
        System.out.println("[2] Find all " + type.name().concat("S"));
        System.out.println("[3] Find all " + type.name().concat("S")
                + " which are matching parameters");
        System.out.println("[4] Go back");
        int choice = view.readInt();
        switch (choice) {
            case 1:
                findById(type);
                break;
            case 2:
                getAll(type);
                break;
            case 3:
                findByParameters(type);
                break;
            case 4:
                execute();
                break;
            default:
                break;
        }
    }

    private void getAll(Type type) {
        if (type == Type.CAR) {
            carController.getAllCars();
        } else if (type == Type.CARSHOWROOM) {
            carshowroomController.getAllCarShowrooms();
        }
    }

    private void findById(Type type) {
        Long id = view.readLong();
        if (type == Type.CAR) {
            carController.getCarById(id);
        }
        if (type == Type.CARSHOWROOM) {
            carshowroomController.getCarShowroomById(id);
        }
    }

    private void findByParameters(Type type) {
        if (type == Type.CAR) {
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
        } else if (type == Type.CARSHOWROOM) {
            System.out.println("Insert parameters:");
            System.out.println("Name: ");
            String name = view.readString();
            System.out.println("city: ");
            String city = view.readString();
            System.out.println("street: ");
            String street = view.readString();
            carshowroomController.getCarShowroomsByParameters(name, city, street);
        }
        execute();
    }

    private Car getCreatedCar() {
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

    private CarShowroom getCreatedCarShoowroom() {
        System.out.println("Insert parameters:");
        System.out.println("name: ");
        String name = view.readString();
        System.out.println("city: ");
        String city = view.readString();
        System.out.println("street: ");
        String street = view.readString();
        System.out.println("Please type IDs of the cars which would you like to"
                + " add and split them with the commas"
                + " if you don't want to add any cars just leave it blank: ");
        String listOfIds = view.readString();
        
        return new CarShowroom(name, city, street);
    }

    private static enum Type {
        CAR,
        CARSHOWROOM
    }

}
