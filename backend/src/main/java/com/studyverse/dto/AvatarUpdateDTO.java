package com.studyverse.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class AvatarUpdateDTO {
    private String email;
    private String avatarUrl;
    private String backgroundPhotoUrl;
}
