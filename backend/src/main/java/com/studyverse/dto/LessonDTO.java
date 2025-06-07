package com.studyverse.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LessonDTO {

    private String title;
    private String subject;
    private String description;
    private String content;
    private Integer difficultyLevel;
    private String imageUrl;

    // 🔸 Opțional – dacă vrei să incluzi quiz-urile în răspuns
    private List<QuizDTO> quizzes;

    private List<Long> quizIds;
    // sau doar
    // private List<Long> quizIds;
}
