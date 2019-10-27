package pl.polsl.szymon.gretka;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import pl.polsl.szymon.gretka.controller.CarController;
import pl.polsl.szymon.gretka.controller.CarshowroomController;
import pl.polsl.szymon.gretka.entity.Car;
import pl.polsl.szymon.gretka.entity.CarShowroom;
import pl.polsl.szymon.gretka.exception.EntityNotFoundException;
import pl.polsl.szymon.gretka.service.CarService;
import pl.polsl.szymon.gretka.service.CarshowroomService;
import pl.polsl.szymon.gretka.view.View;

/**
 * ConsoleMenu is a main menu in of the program
 *
 * @author Szymon Gretka
 * @version 1.0
 */
class ConsoleMenu {

    /**
     * Initialization of the carcontroller
     */
    private final CarController carController;
    /**
     * Initialization of the carshowroomcontroller
     */
    private final CarshowroomController carshowroomController;
    /**
     * Initialization of the carservice
     */
    private final CarService carService;
    /**
     * Initialization of the carshowroomservice
     */
    private final CarshowroomService carshowroomService;
    /**
     * Initialization of the view
     */
    private final View view;

    /**
     * Constructor of the ConsoleMenu class
     *
     * @param carController
     * @param carshowroomController
     * @param carService
     * @param carshowroomService
     * @param view
     */
    public ConsoleMenu(CarController carController,
            CarshowroomController carshowroomController, CarService carService,
            CarshowroomService carshowroomService, View view) {
        this.carController = carController;
        this.carshowroomController = carshowroomController;
        this.carService = carService;
        this.carshowroomService = carshowroomService;
        this.view = view;
    }

    /**
     * Main method of the ConsoleMenu class. It executes all CRUD operations
     * which are implemented in this project.
     */
    public void execute() {

        System.out.println("===========================");
        System.out.println("[0] Add Car to CarShowroom");
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

        boolean bool = true;

        while (bool) {

            switch (selection) {

                case 0:
                    addCarToShowroom();
                    break;
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
                    exit();
                    break;
            }

        }

    }

    private void exit() {
        carService.closeEntityManager();
        carshowroomService.closeEntityManager();
        carService.closeManagerFactory();
        carshowroomService.closeManagerFactory();
        System.exit(1);
    }

    private void addCarToShowroom() {
        System.out.println("Type ID of Carshowroom: ");
        Long carShowroomId = view.readLong();
        CarShowroom carShowroom;
        try {
            carShowroom = carshowroomService.findById(carShowroomId);
        } catch (EntityNotFoundException ex) {
            Logger.getLogger(ConsoleMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Please type IDs of the cars which would you like to"
                + " add and split them with the commas"
                + " if you don't want to add any cars just leave it blank: ");
        String listOfIds = view.readString();
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(listOfIds);
        List<Car> cars = new ArrayList<>();
        if (!listOfIds.isEmpty()) {
            while (m.find()) {
                try {
                    //   carShowroom = carshowroomService.findById(carShowroomId);
                    Long carId = Long.valueOf(m.group());
                    Car car = carService.findById(carId);
                    cars.add(car);
                } catch (EntityNotFoundException ex) {
                    ex.getMessage();
                }
            }
            //carShowroom.setCars(cars);
        }
        execute();
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
        System.out.println("If you want to filter by only part of a string just "
                + "simply put letter with percent sign in suitable place (without quotes!) ");
        System.out.println("Insert parameters:");
        if (type == Type.CAR) {
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
        CarShowroom carShowroom = new CarShowroom();
        System.out.println("Insert parameters:");
        System.out.println("name: ");
        String name = view.readString();
        System.out.println("city: ");
        String city = view.readString();
        System.out.println("street: ");
        String street = view.readString();
        carShowroom.setName(name);
        carShowroom.setCity(city);
        carShowroom.setStreet(street);
        /*System.out.println("Please type IDs of the cars which would you like to"
                + " add and split them with the commas"
                + " if you don't want to add any cars just leave it blank: ");
        String listOfIds = view.readString();
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(listOfIds);
        List<Car> cars = new ArrayList<>();
        if (!listOfIds.isEmpty()) {
            while (m.find()) {
                try {
                    Long carId = Long.valueOf(m.group());
                    Car car = carService.findById(carId);
                    cars.add(car);
                    //carShowroom.addCar(car);
                } catch (EntityNotFoundException ex) {
                    ex.getMessage();
                }
            }
        }     
        carShowroom.setCars(cars);*/
        return carShowroom;
    }

    private static enum Type {
        CAR,
        CARSHOWROOM
    }

}
