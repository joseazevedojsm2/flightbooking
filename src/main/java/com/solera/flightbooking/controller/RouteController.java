package com.solera.flightbooking.controller;

import com.solera.flightbooking.entity.Place;
import com.solera.flightbooking.entity.Route;
import com.solera.flightbooking.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class RouteController {

    @Autowired
    private RouteService service;

    @GetMapping("/routes/places")
    public List<Place> retrieveAllPlaces(){
        return service.getAllPlaces();
    }
    @GetMapping("/routes")
    public List<Route> retrieveAllRoute(){
        return service.getAllRoutes();
    }

    @PostMapping("/routes")
    public Route insertRoute(@RequestBody Route route){
        return service.createRoute(route);
    }

}
