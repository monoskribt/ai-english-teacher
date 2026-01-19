package com.ai.ailanguageteacher.service;

import com.ai.ailanguageteacher.dto.request.SendMessageRequest;
import com.ai.ailanguageteacher.model.Chat;
import com.ai.ailanguageteacher.model.ChatMessage;
import com.ai.ailanguageteacher.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.messages.MessageType;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository repository;

    public void saveUserMessage(SendMessageRequest request, Chat chat, Long userId) {
        var message = new ChatMessage();
        message.setUserId(userId);
        message.setMessageType(MessageType.USER);
        message.setContent(request.getContent());
        message.setChat(chat);

        repository.save(message);
    }

    public void saveAIMessage(String answer, Chat chat) {
        var message = new ChatMessage();
        message.setUserId(null);
        message.setMessageType(MessageType.ASSISTANT);
        message.setContent(answer);
        message.setChat(chat);

        repository.save(message);
    }
}
