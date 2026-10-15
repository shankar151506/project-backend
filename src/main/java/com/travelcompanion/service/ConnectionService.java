package com.travelcompanion.service;

import com.travelcompanion.dto.ConnectionRequestDto;
import com.travelcompanion.entity.ConnectionRequest;
import com.travelcompanion.entity.ConnectionStatus;
import com.travelcompanion.entity.User;
import com.travelcompanion.repository.ConnectionRequestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConnectionService {

    private final ConnectionRequestRepository connectionRequestRepository;
    private final UserService userService;
    private final NotificationService notificationService;

    public ConnectionService(ConnectionRequestRepository connectionRequestRepository,
                            UserService userService,
                            NotificationService notificationService) {
        this.connectionRequestRepository = connectionRequestRepository;
        this.userService = userService;
        this.notificationService = notificationService;
    }

    public ConnectionRequest sendRequest(ConnectionRequestDto request) {
        User sender = userService.getCurrentUser();
        User receiver = userService.getUserById(request.getReceiverId());
        if (sender.getId().equals(receiver.getId())) {
            throw new IllegalArgumentException("You cannot send a request to yourself");
        }
        if (connectionRequestRepository.existsBySenderAndReceiverAndStatus(sender, receiver, ConnectionStatus.PENDING)) {
            throw new IllegalArgumentException("A pending request already exists");
        }

        ConnectionRequest connectionRequest = new ConnectionRequest();
        connectionRequest.setSender(sender);
        connectionRequest.setReceiver(receiver);
        connectionRequest.setMessage(request.getMessage());
        connectionRequest.setStatus(ConnectionStatus.PENDING);

        notificationService.createNotification(receiver, "NEW_CONNECTION_REQUEST", "New connection request from " + sender.getFullName());
        return connectionRequestRepository.save(connectionRequest);
    }

    public List<ConnectionRequest> getReceivedRequests() {
        return connectionRequestRepository.findByReceiver(userService.getCurrentUser());
    }

    public List<ConnectionRequest> getSentRequests() {
        return connectionRequestRepository.findBySender(userService.getCurrentUser());
    }

    public ConnectionRequest acceptRequest(Long requestId) {
        ConnectionRequest request = connectionRequestRepository.findById(requestId)
                .orElseThrow(() -> new IllegalArgumentException("Request not found"));
        request.setStatus(ConnectionStatus.ACCEPTED);
        notificationService.createNotification(request.getSender(), "REQUEST_ACCEPTED", request.getReceiver().getFullName() + " accepted your connection request");
        return connectionRequestRepository.save(request);
    }

    public ConnectionRequest rejectRequest(Long requestId) {
        ConnectionRequest request = connectionRequestRepository.findById(requestId)
                .orElseThrow(() -> new IllegalArgumentException("Request not found"));
        request.setStatus(ConnectionStatus.REJECTED);
        return connectionRequestRepository.save(request);
    }

    public ConnectionRequest cancelRequest(Long requestId) {
        ConnectionRequest request = connectionRequestRepository.findById(requestId)
                .orElseThrow(() -> new IllegalArgumentException("Request not found"));
        request.setStatus(ConnectionStatus.CANCELLED);
        return connectionRequestRepository.save(request);
    }
}
