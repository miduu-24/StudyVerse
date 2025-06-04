package com.studyverse.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Quiz name cannot be empty")
    private String quizName;

    @NotBlank(message = "Quiz description cannot be empty")
    private String quizDescription;

    @NotNull
    private Integer quizDuration; // in minutes

    @NotNull
    private Integer totalQuestions;

    @NotNull
    private Integer totalMarks;

    @NotNull
    private Integer passingMarks;

    @NotNull
    @ManyToMany
    @JoinTable(name = "quiz_question", joinColumns = @JoinColumn(name = "quiz_id"), inverseJoinColumns = @JoinColumn(name = "question_id"))
    private Set<Question> questions;

    @NotNull
    private Boolean quizStatus = false; // true = activ, false = inactiv

    // 🔹 Relația cu lecția părinte
    @ManyToOne
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;
}
