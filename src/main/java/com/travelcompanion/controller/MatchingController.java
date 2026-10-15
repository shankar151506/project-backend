package com.travelcompanion.controller;

import com.travelcompanion.entity.TravelPlan;
import com.travelcompanion.entity.User;
import com.travelcompanion.service.MatchingService;
import com.travelcompanion.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MatchingController {

    private final MatchingService matchingService;
    private final UserService userService;

    public MatchingController(MatchingService matchingService, UserService userService) {
        this.matchingService = matchingService;
        this.userService = userService;
    }

    @GetMapping("/matches")
    public ResponseEntity<List<TravelPlan>> getMatches() {
        User user = userService.getCurrentUser();
        return ResponseEntity.ok(matchingService.findMatchesForUser(user));
    }
}
