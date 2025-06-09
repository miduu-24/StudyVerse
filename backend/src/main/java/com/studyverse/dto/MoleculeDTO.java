package com.studyverse.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

@AllArgsConstructor
public class MoleculeDTO {

    private String name;
    private String symbol;
    private Integer atomicNumber;
    private Double atomicMass;
    private Integer groupNumber;
    private Integer period;
    private String category;

    private String electronShells; // ex: "[2, 8, 1]"
    private Integer protons;
    private Integer neutrons;
    private Integer electrons;
}