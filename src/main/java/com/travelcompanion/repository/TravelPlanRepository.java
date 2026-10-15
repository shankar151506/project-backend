package com.travelcompanion.repository;

import com.travelcompanion.entity.TravelPlan;
import com.travelcompanion.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TravelPlanRepository extends JpaRepository<TravelPlan, Long> {
    List<TravelPlan> findByUser(User user);
    List<TravelPlan> findByDestinationContainingIgnoreCase(String destination);
}
