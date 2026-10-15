package com.travelcompanion.controller;

import com.travelcompanion.dto.ConnectionRequestDto;
import com.travelcompanion.entity.ConnectionRequest;
import com.travelcompanion.service.ConnectionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/connections")
public class ConnectionController {

    private final ConnectionService connectionService;

    public ConnectionController(ConnectionService connectionService) {
        this.connectionService = connectionService;
    }

    @PostMapping
    public ResponseEntity<ConnectionRequest> sendRequest(@Valid @RequestBody ConnectionRequestDto request) {
        return ResponseEntity.ok(connectionService.sendRequest(request));
    }

    @GetMapping("/received")
    public ResponseEntity<List<ConnectionRequest>> getReceivedRequests() {
        return ResponseEntity.ok(connectionService.getReceivedRequests());
    }

    @GetMapping("/sent")
    public ResponseEntity<List<ConnectionRequest>> getSentRequests() {
        return ResponseEntity.ok(connectionService.getSentRequests());
    }

    @PutMapping("/{id}/accept")
    public ResponseEntity<ConnectionRequest> acceptRequest(@PathVariable Long id) {
        return ResponseEntity.ok(connectionService.acceptRequest(id));
    }

    @PutMapping("/{id}/reject")
    public ResponseEntity<ConnectionRequest> rejectRequest(@PathVariable Long id) {
        return ResponseEntity.ok(connectionService.rejectRequest(id));
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<ConnectionRequest> cancelRequest(@PathVariable Long id) {
        return ResponseEntity.ok(connectionService.cancelRequest(id));
    }
}
