package com.studyverse.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionDTO {

    private String question;

    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;

    private String correctAnswer;

    private Integer scoreMath;
    private Integer scoreChemistry;
    private Integer problemHistory;
    private Integer difficultyLevel;
}
