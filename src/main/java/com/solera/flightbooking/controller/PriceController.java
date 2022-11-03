package com.solera.flightbooking.controller;

import com.solera.flightbooking.entity.Price;
import com.solera.flightbooking.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/flights")
@CrossOrigin
public class PriceController {

    @Autowired
    private PriceService service;

    @GetMapping("/prices")
    public List<Price> retrieveAllPrices() {
        return service.getAllPrices();
    }

    @GetMapping("/price")
    public List<Price> retrieveAllPricesByFlight(@RequestParam int flightNumber,@RequestParam int group) {
        return service.getAllPricesByFlight(flightNumber,group);
    }

    @GetMapping("/{origin}/{destination}/groupndate")
    public List<Price> retrieveAllPricesByCompany(@PathVariable String origin,@PathVariable String destination,
                                                 @RequestParam String date,@RequestParam int group) {
        return service.getAllPricesByDateAndGroupAndFlight(origin,destination, LocalDate.parse(date),group);
    }

    @PostMapping("/{name}/price")
    public Price insertPrice(@RequestBody Price price, @RequestParam int flightNumber, @PathVariable String name){
        return service.createPrice(price,flightNumber,name);
    }
}
