package com.solera.flightbooking.controller;

import com.solera.flightbooking.entity.Flight;
import com.solera.flightbooking.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/company/{name}")
public class FlightController {
    @Autowired
    private FlightService service;

    @GetMapping("/")
    public List<Flight> retrieveAllFlightsByCompanyName(@PathVariable String name){
        return service.getAllFlights(name);
    }

    @GetMapping("/{origin}/{destination}/dates")
    public List<Flight> retrieveAllFlightsByCompanyNameAndDateAndRoute(@PathVariable String name, @PathVariable String origin,
                                                                       @PathVariable String destination, @RequestParam String departure){
        return service.getAllFlightsByDateAndRoute(name,origin,destination, LocalDate.parse(departure));
    }

    @PostMapping("/")
    public Flight insertFlight(@PathVariable String name, @RequestBody Flight flight){
        return service.createFlight(flight,name);
    }


}
