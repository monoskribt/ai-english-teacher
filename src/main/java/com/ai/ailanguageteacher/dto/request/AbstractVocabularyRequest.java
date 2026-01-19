package com.ai.ailanguageteacher.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
abstract public class AbstractVocabularyRequest {

    @NotBlank
    private String topic;

    @NotBlank
    private String englishLevel;

    @Max(25)
    @NotNull
    private Integer amount;

}
