package com.travelcompanion.service;

import com.travelcompanion.dto.MessageRequest;
import com.travelcompanion.entity.Message;
import com.travelcompanion.entity.User;
import com.travelcompanion.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final UserService userService;
    private final NotificationService notificationService;

    public MessageService(MessageRepository messageRepository, UserService userService, NotificationService notificationService) {
        this.messageRepository = messageRepository;
        this.userService = userService;
        this.notificationService = notificationService;
    }

    public List<Message> getConversationMessages(Long userId) {
        User currentUser = userService.getCurrentUser();
        User otherUser = userService.getUserById(userId);
        List<Message> messages = messageRepository.findBySenderAndReceiverOrderBySentAtAsc(currentUser, otherUser);
        messages.addAll(messageRepository.findBySenderAndReceiverOrderBySentAtAsc(otherUser, currentUser));
        messages.sort((a, b) -> a.getSentAt().compareTo(b.getSentAt()));
        return messages;
    }

    public Message sendMessage(MessageRequest request) {
        User sender = userService.getCurrentUser();
        User receiver = userService.getUserById(request.getReceiverId());
        Message message = new Message();
        message.setSender(sender);
        message.setReceiver(receiver);
        message.setContent(request.getContent());
        notificationService.createNotification(receiver, "NEW_MESSAGE", sender.getFullName() + " sent you a message");
        return messageRepository.save(message);
    }

    public List<Message> getAllMessagesForUser() {
        User currentUser = userService.getCurrentUser();
        return messageRepository.findBySenderOrReceiverOrderBySentAtAsc(currentUser, currentUser);
    }
}
