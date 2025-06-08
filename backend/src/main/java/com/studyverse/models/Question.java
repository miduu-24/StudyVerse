package com.studyverse.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Question cannot be empty")
    private String question;

    @NotBlank(message = "Option A cannot be empty")
    private String optionA;

    @NotBlank(message = "Option B cannot be empty")
    private String optionB;

    @NotBlank(message = "Option C cannot be empty")
    private String optionC;

    @NotBlank(message = "Option D cannot be empty")
    private String optionD;

    @NotBlank(message = "Correct answer cannot be empty")
    private String correctAnswer;

    private Integer scoreMath = 0;
    private Integer scoreChemistry = 0;

    @Min(1)
    @Max(5)
    private Integer difficultyLevel = 1;

    @ManyToMany(mappedBy = "questions")
    @com.fasterxml.jackson.annotation.JsonIgnore
    private Set<Quiz> quizzes;
}
