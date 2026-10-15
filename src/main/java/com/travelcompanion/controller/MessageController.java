package com.travelcompanion.controller;

import com.travelcompanion.dto.MessageRequest;
import com.travelcompanion.entity.Message;
import com.travelcompanion.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/messages/{userId}")
    public ResponseEntity<List<Message>> getConversation(@PathVariable Long userId) {
        return ResponseEntity.ok(messageService.getConversationMessages(userId));
    }

    @PostMapping("/messages")
    public ResponseEntity<Message> sendMessage(@RequestBody MessageRequest request) {
        return ResponseEntity.ok(messageService.sendMessage(request));
    }
}
