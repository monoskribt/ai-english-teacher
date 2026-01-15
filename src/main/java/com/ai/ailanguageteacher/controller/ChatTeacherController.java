package com.ai.ailanguageteacher.controller;

import com.ai.ailanguageteacher.dto.request.CreateChatRequest;
import com.ai.ailanguageteacher.dto.request.SendMessageRequest;
import com.ai.ailanguageteacher.service.ChatTeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import javax.validation.Valid;

@RestController
@RequestMapping("/chat/teacher")
@RequiredArgsConstructor
public class ChatTeacherController {

    private final ChatTeacherService service;

    @PostMapping("/{userId}")
    public void createChatTeacher(@Valid @RequestBody CreateChatRequest request, @PathVariable Long userId) {
        service.createChat(request, userId);
    }

    @PostMapping(value = "{id}/message/{userId}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> getTeacherAnswer(@Valid @RequestBody SendMessageRequest request,
                                         @PathVariable Long id, @PathVariable Long userId) {
        return service.getTeacherAnswer(request, id, userId);
    }
}
