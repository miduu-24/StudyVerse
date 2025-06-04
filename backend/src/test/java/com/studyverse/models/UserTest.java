package com.studyverse.models;


import jakarta.validation.ConstraintViolation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.validation.Validation;
import jakarta.validation.Validator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

public class UserTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    public void testValidUser() {
        User user = new User(1L, "validUsername", "test@example.com", "password123", 10, 20, 30);
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertTrue(violations.isEmpty(), "The user should be valid.");
    }

    @Test
    public void testInvalidUsername() {
        User user = new User(1L, "ab", "test@example.com", "password123", 10, 20, 30);
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertFalse(violations.isEmpty(), "The username should be between 3 and 20 characters.");
    }

    @Test
    public void testInvalidEmail() {
        User user = new User(1L, "validUsername", "", "password123", 10, 20, 30);
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertFalse(violations.isEmpty(), "Email cannot be empty.");
    }

    @Test
    public void testInvalidPassword() {
        User user = new User(1L, "validUsername", "test@example.com", "", 10, 20, 30);
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertFalse(violations.isEmpty(), "Password cannot be empty.");
    }
}
