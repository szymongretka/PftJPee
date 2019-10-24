package pl.polsl.szymon.gretka.controller;

import java.util.List;
import pl.polsl.szymon.gretka.exception.EntityNotFoundException;
import pl.polsl.szymon.gretka.service.CarshowroomService;
import pl.polsl.szymon.gretka.view.View;
import pl.polsl.szymon.gretka.entity.CarShowroom;

/**
 *
 * @author Szymon Gretka
 */
public class CarshowroomController {

    private final CarshowroomService carshowroomService;
    private final View view;

    public CarshowroomController(CarshowroomService carshowroomService, View view) {
        this.carshowroomService = carshowroomService;
        this.view = view;
    }

    public void getCarShowroomById(final Long id) {
        try {
            CarShowroom carShowroom = carshowroomService.findById(id);
            view.print("CarShowroom: " + carShowroom);
        } catch (EntityNotFoundException e) {
            view.print(e.getMessage());
        }
    }

    public void getAllCarShowrooms() {
        List<CarShowroom> carShowrooms = carshowroomService.getAllCarshowrooms();
        if (!carShowrooms.isEmpty()) {
            view.print("List of CarShowrooms: " + carShowrooms);
        } else {
            view.print("Empty list");
        }
    }

    public void getCarShowroomsByParameters(String name, String city, String street) {
        List<CarShowroom> carShowrooms = carshowroomService
                .getCarshowroomsByParameters(name, city, street);
        if (!carShowrooms.isEmpty()) {
            view.print("List of carshowrooms: " + carShowrooms);
        } else {
            view.print("Empty list");
        }
    }

    public void createCarShowroom(CarShowroom carShowroom) {
        carshowroomService.createCarShowroom(carShowroom);
    }

    public void deleteCarShowroom(final Long id) {
        try {
            carshowroomService.deleteCarShowroom(id);
        } catch (EntityNotFoundException e) {
            view.print(e.getMessage());
        }
    }

    public void updateCarShowroom(final Long id, CarShowroom updatedCarShowroom) {
        carshowroomService.updateCarShowroom(updatedCarShowroom, id);
    }

}
