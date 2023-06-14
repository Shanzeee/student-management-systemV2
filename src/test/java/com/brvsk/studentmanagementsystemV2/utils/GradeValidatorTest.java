package com.brvsk.studentmanagementsystemV2.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GradeValidatorTest {


    private final GradeValidator validator = new GradeValidator();

    @Test
    public void testValidGrades() {
        assertTrue(validator.isValid(2.0, null));
        assertTrue(validator.isValid(2.5, null));
        assertTrue(validator.isValid(3.0, null));
        assertTrue(validator.isValid(3.5, null));
        assertTrue(validator.isValid(4.0, null));
        assertTrue(validator.isValid(4.5, null));
        assertTrue(validator.isValid(5.0, null));
    }

    @Test
    public void testInvalidGrades() {
        assertFalse(validator.isValid(1.5, null));
        assertFalse(validator.isValid(2.3, null));
        assertFalse(validator.isValid(4.05, null));
        assertFalse(validator.isValid(5.5, null));
    }
}