package com.studyverse.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class QuizSubmissionDTO {
    private Long quizId;
    private Long userId;
    private Map<Long, String> answers; // questionId -> răspuns dat
}
