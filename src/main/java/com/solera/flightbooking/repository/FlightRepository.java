package com.solera.flightbooking.repository;

import com.solera.flightbooking.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlightRepository extends JpaRepository<Flight,Integer> {

}
