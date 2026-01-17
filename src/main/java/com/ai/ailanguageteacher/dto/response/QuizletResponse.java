package com.ai.ailanguageteacher.dto.response;

import com.ai.ailanguageteacher.dto.Card;
import com.ai.ailanguageteacher.dto.QuizletType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizletResponse {

    private String topic;
    private String englishLevel;
    private Integer amountOfCards;
    private QuizletType quizletType;
    private List<Card> cards;

}
