package com.ai.ailanguageteacher.dto.request;

import com.ai.ailanguageteacher.dto.PartSpeechType;
import com.ai.ailanguageteacher.dto.QuizletType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizletFilterRequest {

    @NotBlank
    private String topic;

    @NotBlank
    private String englishLevel;

    @NotNull
    private Integer amountOfCards;

    @NotNull
    private QuizletType quizletType;

    @NotEmpty
    private List<PartSpeechType> partSpeech;

}
