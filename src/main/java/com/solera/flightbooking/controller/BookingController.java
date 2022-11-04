package com.solera.flightbooking.controller;

import com.solera.flightbooking.entity.Booking;
import com.solera.flightbooking.entity.Passanger;
import com.solera.flightbooking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class BookingController {
    @Autowired
    private BookingService service;

    @GetMapping("/analytics/{idFlight}/{idPassanger}")
    public Booking getBookingByFlightAndNamePassanger(@PathVariable int idFlight,@PathVariable int idPassanger){
        return service.getBooking(idFlight,idPassanger);
    }

    @PostMapping("/booking/{paidFlight}/{id}")
    public Booking insertBookingByFlightAndidPassanger(@PathVariable int paidFlight,@PathVariable int id){
        return service.createBooking(paidFlight,id);
    }

    @PostMapping("/booking/passanger")
    public Passanger insertPassanger(@RequestBody Passanger passanger){
        return service.createPassanger(passanger);
    }
}
