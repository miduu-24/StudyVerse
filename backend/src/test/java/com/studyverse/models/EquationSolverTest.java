package com.studyverse.models;

import jakarta.validation.ConstraintViolation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

public class EquationSolverTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    public void testValidEquationSolver() {
        EquationSolver equationSolver = new EquationSolver(
            1L, 
            "x + 2 = 5", 
            "x = 3", 
            1L
        );
        Set<ConstraintViolation<EquationSolver>> violations = validator.validate(equationSolver);
        assertTrue(violations.isEmpty(), "The EquationSolver should be valid.");
    }

    @Test
    public void testEmptyEquation() {
        EquationSolver equationSolver = new EquationSolver(
            1L, 
            "", // empty equation
            "x = 3", 
            1L
        );
        Set<ConstraintViolation<EquationSolver>> violations = validator.validate(equationSolver);
        assertFalse(violations.isEmpty(), "Equation cannot be empty.");
    }

    @Test
    public void testEmptySolution() {
        EquationSolver equationSolver = new EquationSolver(
            1L, 
            "x + 2 = 5", 
            "", // empty solution
            1L
        );
        Set<ConstraintViolation<EquationSolver>> violations = validator.validate(equationSolver);
        assertFalse(violations.isEmpty(), "Solution cannot be empty.");
    }

    @Test
    public void testEmptyUserId() {
        EquationSolver equationSolver = new EquationSolver(
            1L, 
            "x + 2 = 5", 
            "x = 3", 
            null // empty userId (null)
        );
        Set<ConstraintViolation<EquationSolver>> violations = validator.validate(equationSolver);
        assertFalse(violations.isEmpty(), "UserId cannot be empty.");
    }

    @Test
    public void testInvalidUserId() {
        EquationSolver equationSolver = new EquationSolver(
            1L, 
            "x + 2 = 5", 
            "x = 3", 
            -1L // invalid userId (negative value)
        );
        Set<ConstraintViolation<EquationSolver>> violations = validator.validate(equationSolver);
        assertFalse(violations.isEmpty(), "UserId must be a valid positive value.");
    }
}
