package com.travelcompanion.repository;

import com.travelcompanion.entity.Message;
import com.travelcompanion.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findBySenderOrReceiverOrderBySentAtAsc(User sender, User receiver);
    List<Message> findBySenderAndReceiverOrderBySentAtAsc(User sender, User receiver);
    List<Message> findByReceiverAndReadStatus(User receiver, boolean readStatus);
}
