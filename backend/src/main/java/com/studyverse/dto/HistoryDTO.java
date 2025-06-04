package com.studyverse.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter 

public class HistoryDTO {
    private Long userId;
    private Long lessonId;
    private Long quizId;
}