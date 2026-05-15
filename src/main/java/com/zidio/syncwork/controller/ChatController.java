package com.zidio.syncwork.controller;

import com.zidio.syncwork.entity.ChatMessage;
import com.zidio.syncwork.repository.ChatMessageRepository;
import com.zidio.syncwork.service.ChatService; // Import the new service
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin(origins = "*")
public class ChatController {

    @Autowired
    private ChatService chatService; // Use the service instead of the repository directly

    @Autowired
    private ChatMessageRepository chatMessageRepository; // Keep for history for now

    @GetMapping("/history")
    public List<ChatMessage> getChatHistory() {
        return chatMessageRepository.findAll();
    }

    /**
     * Handles new messages. It now uses the service to save the message
     * and returns the complete message object (with ID) to all clients.
     */
    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        // The service saves the message and returns the instance with the ID
        return chatService.saveMessage(chatMessage);
    }

    /**
     * Handles deleting a message. It uses the service for a transactional delete
     * and returns a confirmation payload with the ID of the deleted message.
     */
    @MessageMapping("/chat.unsendMessage")
    @SendTo("/topic/public")
    public Map<String, Long> unsendMessage(@Payload Map<String, Long> payload) {
        Long messageId = payload.get("id");
        chatService.deleteMessage(messageId);
        return Map.of("deletedMessageId", messageId);
    }
}