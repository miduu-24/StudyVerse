package com.studyverse.models;

import jakarta.validation.ConstraintViolation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import jakarta.validation.Validation;
import jakarta.validation.Validator;


import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MoleculeTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    public void testValidMolecule() {
        Molecule molecule = new Molecule(
            1L,
            "Water",
            "H2O",
            "/models/water_3d_path.obj"
        );
        Set<ConstraintViolation<Molecule>> violations = validator.validate(molecule);
        assertTrue(violations.isEmpty(), "The molecule should be valid.");
    }

    @Test
    public void testEmptyName() {
        Molecule molecule = new Molecule(
            1L,
            "",
            "H2O",
            "/models/water_3d_path.obj"
        );
        Set<ConstraintViolation<Molecule>> violations = validator.validate(molecule);
        assertFalse(violations.isEmpty(), "Name cannot be empty.");
    }

    @Test
    public void testEmptyFormula() {
        Molecule molecule = new Molecule(
            1L,
            "Water",
            "",
            "/models/water_3d_path.obj"
        );
        Set<ConstraintViolation<Molecule>> violations = validator.validate(molecule);
        assertFalse(violations.isEmpty(), "Formula cannot be empty.");
    }

    @Test
    public void testEmptyModel3DPath() {
        Molecule molecule = new Molecule(
            1L,
            "Water",
            "H2O",
            ""
        );
        Set<ConstraintViolation<Molecule>> violations = validator.validate(molecule);
        assertFalse(violations.isEmpty(), "Model3DPath cannot be empty.");
    }
}
