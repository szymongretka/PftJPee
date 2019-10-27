package pl.polsl.szymon.gretka.controller;

import java.util.List;
import pl.polsl.szymon.gretka.exception.EntityNotFoundException;
import pl.polsl.szymon.gretka.service.CarshowroomService;
import pl.polsl.szymon.gretka.view.View;
import pl.polsl.szymon.gretka.entity.CarShowroom;

/**
 * Carshowroom controller which encapsulates all data and returns the view
 * 
 * @author Szymon Gretka
 */
public class CarshowroomController {

    private final CarshowroomService carshowroomService;
    private final View view;

    /**
     * Constructor for creating a new carshowroomcontroller.
     * @param carshowroomService
     * @param view
     */
    public CarshowroomController(CarshowroomService carshowroomService, View view) {
        this.carshowroomService = carshowroomService;
        this.view = view;
    }

    /**
     * Returns the view of the carshowroom with given id 
     * @param id
     */
    public void getCarShowroomById(final Long id) {
        try {
            CarShowroom carShowroom = carshowroomService.findById(id);
            view.print("CarShowroom: " + carShowroom);
        } catch (EntityNotFoundException e) {
            view.print(e.getMessage());
        }
    }

    /**
     * Returns list of all carshowrooms
     */
    public void getAllCarShowrooms() {
        List<CarShowroom> carShowrooms = carshowroomService.getAllCarshowrooms();
        if (!carShowrooms.isEmpty()) {
            view.print("List of CarShowrooms: " + carShowrooms);
        } else {
            view.print("Empty list");
        }
    }

    /**
     * Returns list of carshowrooms which fulfill given parameters
     * @param name
     * @param city
     * @param street
     */
    public void getCarShowroomsByParameters(String name, String city, String street) {
        List<CarShowroom> carShowrooms = carshowroomService
                .getCarshowroomsByParameters(name, city, street);
        if (!carShowrooms.isEmpty()) {
            view.print("List of carshowrooms: " + carShowrooms);
        } else {
            view.print("Empty list");
        }
    }

    /**
     * Creates a new carshowroom
     * @param carShowroom
     */
    public void createCarShowroom(CarShowroom carShowroom) {
        carshowroomService.createCarShowroom(carShowroom);
    }

    /**
     * Deletes the carshowroom
     * @param id
     */
    public void deleteCarShowroom(final Long id) {
        try {
            carshowroomService.deleteCarShowroom(id);
        } catch (EntityNotFoundException e) {
            view.print(e.getMessage());
        }
    }

    /**
     * Updates the carshowroom with given id
     * @param id
     * @param updatedCarShowroom
     */
    public void updateCarShowroom(final Long id, CarShowroom updatedCarShowroom) {
        carshowroomService.updateCarShowroom(updatedCarShowroom, id);
    }

}
