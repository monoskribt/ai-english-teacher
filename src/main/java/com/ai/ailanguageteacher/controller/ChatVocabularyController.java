package com.ai.ailanguageteacher.controller;

import com.ai.ailanguageteacher.dto.request.QuizletFilterRequest;
import com.ai.ailanguageteacher.dto.response.QuizletResponse;
import com.ai.ailanguageteacher.service.ChatVocabularyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/chat/vocabulary")
@RequiredArgsConstructor
public class ChatVocabularyController {

    private final ChatVocabularyService vocabularyService;

    @PostMapping("/quizlet/{userId}")
    public QuizletResponse generateQuizlet(@Valid @RequestBody QuizletFilterRequest request,
                                           @PathVariable Long userId) {
        return vocabularyService.generateQuizlet(request, userId);
    }
}
