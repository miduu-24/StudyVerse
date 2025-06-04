package com.studyverse.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class UserDTO {
    private String username;
    private String password;
    private String email;
    private Integer scoreMath ;
    private Integer scoreChemistry ;
    private Integer problemHistory ;
}