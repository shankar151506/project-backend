package com.travelcompanion.repository;

import com.travelcompanion.entity.Notification;
import com.travelcompanion.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByUserOrderByCreatedAtDesc(User user);
    List<Notification> findByUserAndReadStatus(User user, boolean readStatus);
}
