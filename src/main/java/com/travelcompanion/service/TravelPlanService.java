package com.travelcompanion.service;

import com.travelcompanion.dto.TravelPlanRequest;
import com.travelcompanion.entity.TravelPlan;
import com.travelcompanion.entity.User;
import com.travelcompanion.repository.TravelPlanRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TravelPlanService {

    private final TravelPlanRepository travelPlanRepository;
    private final UserService userService;

    public TravelPlanService(TravelPlanRepository travelPlanRepository, UserService userService) {
        this.travelPlanRepository = travelPlanRepository;
        this.userService = userService;
    }

    public TravelPlan createTravelPlan(TravelPlanRequest request) {
        User user = userService.getCurrentUser();
        TravelPlan plan = new TravelPlan();
        plan.setUser(user);
        plan.setDestination(request.getDestination());
        plan.setStartingLocation(request.getStartingLocation());
        plan.setDepartureDate(request.getDepartureDate());
        plan.setReturnDate(request.getReturnDate());
        plan.setTripDuration(request.getTripDuration());
        plan.setBudget(request.getBudget());
        plan.setCurrency(request.getCurrency() != null ? request.getCurrency() : "INR");
        plan.setTravelStyle(request.getTravelStyle());
        plan.setInterests(request.getInterests() == null ? java.util.Collections.emptySet() : request.getInterests());
        plan.setActivities(request.getActivities() == null ? java.util.Collections.emptySet() : request.getActivities());
        plan.setAccommodationPreference(request.getAccommodationPreference());
        plan.setTransportationPreference(request.getTransportationPreference());
        plan.setCompanionsNeeded(request.getCompanionsNeeded());
        plan.setDescription(request.getDescription());
        return travelPlanRepository.save(plan);
    }

    public List<TravelPlan> getAllPlans() {
        return travelPlanRepository.findAll();
    }

    public TravelPlan getPlanById(Long id) {
        return travelPlanRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Travel plan not found: " + id));
    }

    public List<TravelPlan> getMyPlans() {
        return travelPlanRepository.findByUser(userService.getCurrentUser());
    }

    public TravelPlan updateTravelPlan(Long id, TravelPlanRequest request) {
        TravelPlan plan = getPlanById(id);
        if (!plan.getUser().getId().equals(userService.getCurrentUser().getId())) {
            throw new IllegalArgumentException("You cannot update this trip");
        }
        plan.setDestination(request.getDestination());
        plan.setStartingLocation(request.getStartingLocation());
        plan.setDepartureDate(request.getDepartureDate());
        plan.setReturnDate(request.getReturnDate());
        plan.setTripDuration(request.getTripDuration());
        plan.setBudget(request.getBudget());
        if (request.getCurrency() != null) plan.setCurrency(request.getCurrency());
        plan.setTravelStyle(request.getTravelStyle());
        if (request.getInterests() != null) plan.setInterests(request.getInterests());
        if (request.getActivities() != null) plan.setActivities(request.getActivities());
        plan.setAccommodationPreference(request.getAccommodationPreference());
        plan.setTransportationPreference(request.getTransportationPreference());
        plan.setCompanionsNeeded(request.getCompanionsNeeded());
        plan.setDescription(request.getDescription());
        return travelPlanRepository.save(plan);
    }

    public void deleteTravelPlan(Long id) {
        TravelPlan plan = getPlanById(id);
        if (!plan.getUser().getId().equals(userService.getCurrentUser().getId())) {
            throw new IllegalArgumentException("You cannot delete this trip");
        }
        travelPlanRepository.delete(plan);
    }
}
