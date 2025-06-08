package com.studyverse.models;

import jakarta.validation.ConstraintViolation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class QuestionTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    public void testValidQuestion() {
        Question question = new Question(
                1L,
                "What is the capital of France?",
                "Paris",
                "London",
                "Berlin",
                "Madrid",
                "A",
                10,
                0,
                0,

                null);

        Set<ConstraintViolation<Question>> violations = validator.validate(question);
        assertTrue(violations.isEmpty(), "The question should be valid.");
    }

    @Test
    public void testEmptyQuestionText() {
        Question question = new Question(
                1L,
                "",
                "A", "B", "C", "D",
                "A",
                10, 0, 0,
                null);

        Set<ConstraintViolation<Question>> violations = validator.validate(question);
        assertFalse(violations.isEmpty(), "Question text cannot be empty.");
    }

    @Test
    public void testEmptyCorrectAnswer() {
        Question question = new Question(
                1L,
                "What is the capital of France?",
                "Paris",
                "London",
                "Berlin",
                "Madrid",
                "",
                10, 0, 0,
                null);

        Set<ConstraintViolation<Question>> violations = validator.validate(question);
        assertFalse(violations.isEmpty(), "Correct answer cannot be empty.");
    }

    @Test
    public void testInvalidDifficultyLevel() {
        Question question = new Question(
                1L,
                "What is the capital of France?",
                "Paris",
                "London",
                "Berlin",
                "Madrid",
                "A",
                10, 0, 0, // Invalid
                null);

        Set<ConstraintViolation<Question>> violations = validator.validate(question);
        assertFalse(violations.isEmpty(), "Difficulty level must be between 1 and 5.");
    }

    @Test
    public void testValidDifficultyLevel() {
        Question question = new Question(
                1L,
                "What is the capital of France?",
                "Paris",
                "London",
                "Berlin",
                "Madrid",
                "A",
                10, 0, 0,
                null);

        Set<ConstraintViolation<Question>> violations = validator.validate(question);
        assertTrue(violations.isEmpty(), "Difficulty level is valid.");
    }
}
