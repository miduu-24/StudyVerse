package com.studyverse.models;

import jakarta.validation.ConstraintViolation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

public class HistoryTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    public void testValidHistory() {
        History history = new History(
            1L,
            1L, // valid userId
            1L, // valid lessonId
            1L  // valid quizId
        );
        Set<ConstraintViolation<History>> violations = validator.validate(history);
        assertTrue(violations.isEmpty(), "The history entry should be valid.");
    }

    @Test
    public void testEmptyUserId() {
        History history = new History(
            1L,
            null, // userId cannot be null
            1L,   // valid lessonId
            1L    // valid quizId
        );
        Set<ConstraintViolation<History>> violations = validator.validate(history);
        assertFalse(violations.isEmpty(), "UserId cannot be null.");
    }

    @Test
    public void testInvalidUserId() {
        History history = new History(
            1L,
            -1L, // invalid userId (negative number)
            1L,  // valid lessonId
            1L   // valid quizId
        );
        Set<ConstraintViolation<History>> violations = validator.validate(history);
        assertFalse(violations.isEmpty(), "UserId must be a valid positive value.");
    }
}
