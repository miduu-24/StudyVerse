package com.studyverse.models;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class QuizTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    private Quiz buildQuiz(String name, String desc, Integer duration, Integer totalQuestions,
            Integer totalMarks, Integer passingMarks, Set<Question> questions,
            Boolean status) {

        Lesson dummyLesson = new Lesson(); // poate fi null, dar adăugăm un obiect valid
        return new Quiz(
                1L,
                name,
                desc,
                duration,
                totalQuestions,
                totalMarks,
                passingMarks,
                questions,
                status,
                dummyLesson);
    }

    @Test
    public void testValidQuiz() {
        Quiz quiz = buildQuiz(
                "Sample Quiz",
                "This is a sample quiz",
                30,
                10,
                100,
                50,
                new HashSet<>(),
                true);

        Set<ConstraintViolation<Quiz>> violations = validator.validate(quiz);
        assertTrue(violations.isEmpty(), "The quiz should be valid.");
    }

    @Test
    public void testEmptyQuizName() {
        Quiz quiz = buildQuiz(
                "",
                "Sample description",
                30,
                10,
                100,
                50,
                new HashSet<>(),
                true);
        Set<ConstraintViolation<Quiz>> violations = validator.validate(quiz);
        assertFalse(violations.isEmpty(), "Quiz name cannot be empty.");
    }

    @Test
    public void testEmptyQuizDescription() {
        Quiz quiz = buildQuiz(
                "Sample Quiz",
                "",
                30,
                10,
                100,
                50,
                new HashSet<>(),
                true);
        Set<ConstraintViolation<Quiz>> violations = validator.validate(quiz);
        assertFalse(violations.isEmpty(), "Quiz description cannot be empty.");
    }

    @Test
    public void testNullQuizDuration() {
        Quiz quiz = buildQuiz(
                "Sample Quiz",
                "Sample description",
                null,
                10,
                100,
                50,
                new HashSet<>(),
                true);
        Set<ConstraintViolation<Quiz>> violations = validator.validate(quiz);
        assertFalse(violations.isEmpty(), "Quiz duration cannot be null.");
    }

    @Test
    public void testNullTotalQuestions() {
        Quiz quiz = buildQuiz(
                "Sample Quiz",
                "Sample description",
                30,
                null,
                100,
                50,
                new HashSet<>(),
                true);
        Set<ConstraintViolation<Quiz>> violations = validator.validate(quiz);
        assertFalse(violations.isEmpty(), "Quiz total questions cannot be null.");
    }

    @Test
    public void testNullQuestions() {
        Quiz quiz = buildQuiz(
                "Sample Quiz",
                "Sample description",
                30,
                10,
                100,
                50,
                null,
                true);
        Set<ConstraintViolation<Quiz>> violations = validator.validate(quiz);
        assertFalse(violations.isEmpty(), "Questions cannot be null.");
    }

    @Test
    public void testNullQuizStatus() {
        Quiz quiz = buildQuiz(
                "Sample Quiz",
                "Sample description",
                30,
                10,
                100,
                50,
                new HashSet<>(),
                null);
        Set<ConstraintViolation<Quiz>> violations = validator.validate(quiz);
        assertFalse(violations.isEmpty(), "Quiz status cannot be null.");
    }
}
