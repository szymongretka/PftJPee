/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.szymon.gretka.controller;

import pl.polsl.szymon.gretka.service.CarshowroomService;
import pl.polsl.szymon.gretka.view.View;

/**
 *
 * @author Szymek
 */
public class CarshowroomController {
    
    private final CarshowroomService carshowroomService;
    private final View view;

    public CarshowroomController(CarshowroomService carshowroomService, View view) {
        this.carshowroomService = carshowroomService;
        this.view = view;
    }
    
    
}
