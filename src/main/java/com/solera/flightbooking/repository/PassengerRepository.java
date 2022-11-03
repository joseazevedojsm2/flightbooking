package com.solera.flightbooking.repository;

import com.solera.flightbooking.entity.Passanger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository extends JpaRepository<Passanger,Integer> {
}
