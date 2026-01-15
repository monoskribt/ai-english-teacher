package com.ai.ailanguageteacher.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ChatVocabularyService {

    private final ChatClient chatClient;

    public ChatVocabularyService(@Qualifier("chatClientVocabulary") ChatClient chatClient) {
        this.chatClient = chatClient;
    }
}
