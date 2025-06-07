package com.studyverse.models;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LessonTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    private Lesson buildLesson(String title, String subject, String description, String content, int difficultyLevel) {
        return new Lesson(
                1L,
                title,
                subject,
                description,
                content,
                difficultyLevel,
                "https://example.com/image.png",
                new ArrayList<>() // quizzes
        );
    }

    @Test
    public void testValidLesson() {
        Lesson lesson = buildLesson(
                "Java Basics",
                "Informatics",
                "Introductory Java concepts.",
                "Lesson content here.",
                3);
        Set<ConstraintViolation<Lesson>> violations = validator.validate(lesson);
        assertTrue(violations.isEmpty(), "The lesson should be valid.");
    }

    @Test
    public void testEmptyTitle() {
        Lesson lesson = buildLesson(
                "",
                "Informatics",
                "Description ok",
                "Content ok",
                2);
        Set<ConstraintViolation<Lesson>> violations = validator.validate(lesson);
        assertFalse(violations.isEmpty(), "Title cannot be empty.");
    }

    @Test
    public void testEmptyDescription() {
        Lesson lesson = buildLesson(
                "Java Basics",
                "Informatics",
                "",
                "Some content",
                3);
        Set<ConstraintViolation<Lesson>> violations = validator.validate(lesson);
        assertFalse(violations.isEmpty(), "Description cannot be empty.");
    }

    @Test
    public void testEmptyContent() {
        Lesson lesson = buildLesson(
                "Java Basics",
                "Informatics",
                "Some description",
                "",
                2);
        Set<ConstraintViolation<Lesson>> violations = validator.validate(lesson);
        assertFalse(violations.isEmpty(), "Content cannot be empty.");
    }

    @Test
    public void testDifficultyLevelTooLow() {
        Lesson lesson = buildLesson(
                "Java Basics",
                "Informatics",
                "Valid description",
                "Valid content",
                0);
        Set<ConstraintViolation<Lesson>> violations = validator.validate(lesson);
        assertFalse(violations.isEmpty(), "Difficulty level must be at least 1.");
    }

    @Test
    public void testDifficultyLevelTooHigh() {
        Lesson lesson = buildLesson(
                "Java Basics",
                "Informatics",
                "Valid description",
                "Valid content",
                6);
        Set<ConstraintViolation<Lesson>> violations = validator.validate(lesson);
        assertFalse(violations.isEmpty(), "Difficulty level must be at most 5.");
    }

    @Test
    public void testValidDifficultyLevelBoundaries() {
        Lesson lessonLow = buildLesson("T", "D", "C", "Content", 1);
        Lesson lessonHigh = buildLesson("T", "D", "C", "Content", 5);

        assertTrue(validator.validate(lessonLow).isEmpty(), "Level 1 should be valid");
        assertTrue(validator.validate(lessonHigh).isEmpty(), "Level 5 should be valid");
    }
}
