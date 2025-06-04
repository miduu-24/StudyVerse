package com.studyverse.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Setter
@Getter

public class QuizDTO {

    private String quizName;
    private String quizDescription;
    private Integer quizDuration;
    private Integer totalQuestions;
    private Integer totalMarks;
    private Integer passingMarks;
    private Boolean quizStatus;

    private Set<Long> questionIds; // ID-urile întrebărilor

    private Long lessonId; // 🔹 ID-ul lecției de care aparține quiz-ul
}
