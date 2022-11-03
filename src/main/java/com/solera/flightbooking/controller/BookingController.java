package com.solera.flightbooking.controller;

import com.solera.flightbooking.entity.Booking;
import com.solera.flightbooking.entity.Passanger;
import com.solera.flightbooking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
@CrossOrigin
public class BookingController {
    @Autowired
    private BookingService service;

    @GetMapping("/{idBooking}/{name}")
    public Booking getBookingByFlightAndNamePassanger(@PathVariable int idBooking,@PathVariable String name){
        return service.getBooking(idBooking,name);
    }

    @PostMapping("/{paidFlight}/{id}")
    public Booking insertBookingByFlightAndidPassanger(@PathVariable int paidFlight,@PathVariable int id){
        return service.createBooking(paidFlight,id);
    }

    @PostMapping("/passanger")
    public Passanger insertPassanger(@RequestBody Passanger passanger){
        return service.createPassanger(passanger);
    }
}
