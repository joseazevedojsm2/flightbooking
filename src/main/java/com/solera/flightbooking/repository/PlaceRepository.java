package com.solera.flightbooking.repository;

import com.solera.flightbooking.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlaceRepository extends JpaRepository<Place,Integer> {
     List<Place> findByName(String name);
}
