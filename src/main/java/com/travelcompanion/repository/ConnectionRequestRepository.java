package com.travelcompanion.repository;

import com.travelcompanion.entity.ConnectionRequest;
import com.travelcompanion.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConnectionRequestRepository extends JpaRepository<ConnectionRequest, Long> {
    List<ConnectionRequest> findByReceiver(User receiver);
    List<ConnectionRequest> findBySender(User sender);
    boolean existsBySenderAndReceiverAndStatus(User sender, User receiver, com.travelcompanion.entity.ConnectionStatus status);
}
