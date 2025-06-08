package com.studyverse.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QuizWithQuestionsDTO {
    private Long id;
    private String quizName;
    private String quizDescription;
    private Integer quizDuration;
    private Integer totalQuestions;
    private Integer totalMarks;
    private Integer passingMarks;
    private Boolean quizStatus;

    private List<QuestionDTO> questions;
}
