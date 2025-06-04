package com.studyverse.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Setter
@Getter

public class NotificationDTO{
    private String title;
    private String message;
    private Long userId;
    private LocalDateTime createdAt;
}