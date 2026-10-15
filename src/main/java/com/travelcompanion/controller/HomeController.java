package com.travelcompanion.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HomeController {

    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> root() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "UP");
        response.put("service", "Travel Companion Backend API");
        response.put("version", "1.0.0");
        response.put("frontendUrl", "https://frontend-peach-eight-99.vercel.app");
        response.put("healthCheck", "/actuator/health");
        response.put("message", "Welcome! The Travel Companion API is running smoothly.");
        return ResponseEntity.ok(response);
    }
}
