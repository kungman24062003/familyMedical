package com.example.backend.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    private final ChatClient chatClient;

    public ChatService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public String getReply(String userMessage) {
        // Send a message using ChatClient
        UserMessage message = new UserMessage(userMessage);
        return chatClient.prompt()
                .user(userMessage)
                .call()
                .content();
    }
}