package com.studyverse.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class EquationSolverDTO {
    private String equation;
    private String solution;
    private Long userId;
}