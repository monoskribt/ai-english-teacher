package com.ai.ailanguageteacher.controller;

import com.ai.ailanguageteacher.dto.request.VocabularyQuizletFilterRequest;
import com.ai.ailanguageteacher.dto.response.VocabularyResponse;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/chat/vocabulary")
public class ChatVocabularyController {

    @PostMapping("/quizlet/{userId}")
    public VocabularyResponse createQuizlet(@Valid @RequestBody VocabularyQuizletFilterRequest request,
                                            @PathVariable Long userId) {
        return null;
    }
}
