package com.travelcompanion.controller;

import com.travelcompanion.dto.TravelPlanRequest;
import com.travelcompanion.entity.TravelPlan;
import com.travelcompanion.service.TravelPlanService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TravelPlanController {

    private final TravelPlanService travelPlanService;

    public TravelPlanController(TravelPlanService travelPlanService) {
        this.travelPlanService = travelPlanService;
    }

    @PostMapping("/trips")
    public ResponseEntity<TravelPlan> createTravelPlan(@Valid @RequestBody TravelPlanRequest request) {
        return ResponseEntity.ok(travelPlanService.createTravelPlan(request));
    }

    @GetMapping("/trips")
    public ResponseEntity<List<TravelPlan>> getAllTrips() {
        return ResponseEntity.ok(travelPlanService.getAllPlans());
    }

    @GetMapping("/trips/{id}")
    public ResponseEntity<TravelPlan> getTrip(@PathVariable Long id) {
        return ResponseEntity.ok(travelPlanService.getPlanById(id));
    }

    @PutMapping("/trips/{id}")
    public ResponseEntity<TravelPlan> updateTrip(@PathVariable Long id, @Valid @RequestBody TravelPlanRequest request) {
        return ResponseEntity.ok(travelPlanService.updateTravelPlan(id, request));
    }

    @DeleteMapping("/trips/{id}")
    public ResponseEntity<Void> deleteTrip(@PathVariable Long id) {
        travelPlanService.deleteTravelPlan(id);
        return ResponseEntity.noContent().build();
    }
}
