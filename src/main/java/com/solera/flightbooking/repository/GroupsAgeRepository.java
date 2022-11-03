package com.solera.flightbooking.repository;

import com.solera.flightbooking.entity.GroupsAge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupsAgeRepository extends JpaRepository<GroupsAge,Integer> {
}
