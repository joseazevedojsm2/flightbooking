package com.solera.flightbooking.service;

import com.solera.flightbooking.entity.Booking;
import com.solera.flightbooking.entity.Passanger;
import com.solera.flightbooking.entity.Price;
import com.solera.flightbooking.repository.BookingRepository;
import com.solera.flightbooking.repository.PassengerRepository;
import com.solera.flightbooking.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BookingService {
    private BookingRepository repository;
    private PassengerRepository passengerRepository;
    private PriceRepository priceRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository,
                          PassengerRepository passengerRepository, PriceRepository priceRepository){
        repository = bookingRepository;
        this.passengerRepository = passengerRepository;
        this.priceRepository = priceRepository;
    }

    public Passanger createPassanger(Passanger passanger){
        if(passanger.getfName().isEmpty()||passanger.getlName().isEmpty())
            return null;
        return passengerRepository.save(passanger);
    }

    public Booking createBooking(int fk_price, int fk_passanger){

        Optional<Price> oPrice = priceRepository.findById(fk_price);
        Optional<Passanger> oPassanger = passengerRepository.findById(fk_passanger);

        if(oPrice.isEmpty()||oPassanger.isEmpty())
            return null;

        Booking booking = new Booking(oPassanger.get(),oPrice.get());
        booking = repository.save(booking);

        return booking;
    }
    public Booking getBooking(int idFlight, int idPassanger) {
        return repository.findAll().stream().filter(booking -> {
            return booking.getPassanger().getId()==idPassanger &&
                    booking.getPrice().getFlight().getId() == idFlight;
        }).findFirst().get();
    }
}
