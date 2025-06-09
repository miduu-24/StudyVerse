package com.studyverse.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Molecule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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