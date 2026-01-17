package com.ai.ailanguageteacher.service;

import com.ai.ailanguageteacher.dto.request.QuizletFilterRequest;
import com.ai.ailanguageteacher.dto.response.QuizletResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import static org.springframework.ai.chat.memory.ChatMemory.CONVERSATION_ID;

@Service
public class ChatVocabularyService {

    private final ChatClient chatClient;
    private final ObjectMapper mapper;

    @Value("classpath:/prompt-templates/quizlet-system.st")
    Resource quizletPrompt;

    public ChatVocabularyService(@Qualifier("chatClientVocabulary") ChatClient chatClient, ObjectMapper mapper) {
        this.chatClient = chatClient;
        this.mapper = mapper;
    }

    public QuizletResponse generateQuizlet(QuizletFilterRequest request, Long userId) {
        return chatClient.prompt()
                .user(toJson(request))
                .system(quizletPrompt)
                .advisors(a -> a.param(CONVERSATION_ID, userId))
                .call()
                .responseEntity(QuizletResponse.class)
                .entity();
    }

    private String toJson(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed during converting request to json format");
        }
    }
}
