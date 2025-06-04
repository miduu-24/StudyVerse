package com.studyverse.models;

import jakarta.validation.ConstraintViolation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

import java.time.LocalDateTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NotificationTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    public void testValidNotification() {
        Notification notification = new Notification(
            1L,
            "Test Title",
            "This is a test message",
            123L,
            LocalDateTime.now()
        );
        Set<ConstraintViolation<Notification>> violations = validator.validate(notification);
        assertTrue(violations.isEmpty(), "The notification should be valid.");
    }

    @Test
    public void testEmptyTitle() {
        Notification notification = new Notification(
            1L,
            "",
            "This is a test message",
            123L,
            LocalDateTime.now()
        );
        Set<ConstraintViolation<Notification>> violations = validator.validate(notification);
        assertFalse(violations.isEmpty(), "Title cannot be empty.");
    }

    @Test
    public void testEmptyMessage() {
        Notification notification = new Notification(
            1L,
            "Test Title",
            "",
            123L,
            LocalDateTime.now()
        );
        Set<ConstraintViolation<Notification>> violations = validator.validate(notification);
        assertFalse(violations.isEmpty(), "Message cannot be empty.");
    }

    @Test
    public void testNullUserId() {
        Notification notification = new Notification(
            1L,
            "Test Title",
            "This is a test message",
            null,
            LocalDateTime.now()
        );
        Set<ConstraintViolation<Notification>> violations = validator.validate(notification);
        assertFalse(violations.isEmpty(), "UserId cannot be null.");
    }

    @Test
    public void testValidUserId() {
        Notification notification = new Notification(
            1L,
            "Test Title",
            "This is a test message",
            123L,
            LocalDateTime.now()
        );
        Set<ConstraintViolation<Notification>> violations = validator.validate(notification);
        assertTrue(violations.isEmpty(), "UserId should be valid.");
    }
}
