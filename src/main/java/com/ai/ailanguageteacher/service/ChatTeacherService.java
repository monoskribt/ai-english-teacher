package com.ai.ailanguageteacher.service;

import com.ai.ailanguageteacher.dto.request.CreateChatRequest;
import com.ai.ailanguageteacher.dto.request.SendMessageRequest;
import com.ai.ailanguageteacher.model.Chat;
import com.ai.ailanguageteacher.repository.ChatRepository;
import com.ai.ailanguageteacher.repository.UserRepository;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;

import java.util.NoSuchElementException;

import static org.springframework.ai.chat.memory.ChatMemory.CONVERSATION_ID;

@Service
public class ChatTeacherService {

    private final ChatClient chatClient;
    private final ChatRepository repository;
    private final UserRepository userRepository;
    private final MessageService messageService;

    @Value("classpath:/prompt-templates/english-teacher-system.st")
    Resource englishTeacherPrompt;

    public ChatTeacherService(@Qualifier("chatClientEnglishTeacher") ChatClient chatClient,
                              ChatRepository repository, UserRepository userRepository, MessageService messageService) {
        this.chatClient = chatClient;
        this.repository = repository;
        this.userRepository = userRepository;
        this.messageService = messageService;
    }

    @Transactional
    public void createChat(CreateChatRequest request, Long userId) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User with id: %d is not present".formatted(userId)));

        var chat = new Chat();
        chat.setChatUser(user);
        chat.setType(request.getType());

        repository.save(chat);
    }

    @Transactional
    public Flux<String> getTeacherAnswer(SendMessageRequest request, Long chatId, Long userId) {
        var chat = getChat(chatId);
        var fullAIAnswer = new StringBuilder();

        messageService.saveUserMessage(request, chat, userId);

        return chatClient.prompt()
                .user(request.getContent())
                .system(englishTeacherPrompt)
                .advisors(a -> a.param(CONVERSATION_ID, userId))
                .stream()
                .content()
                .doOnNext(fullAIAnswer::append)
                .doOnComplete(() -> messageService.saveAIMessage(fullAIAnswer.toString(), chat));
    }

    private Chat getChat(Long chatId) {
        return repository.findById(chatId)
                .orElseThrow(() -> new NoSuchElementException("Chat is not present with id: %d".formatted(chatId)));
    }
}
