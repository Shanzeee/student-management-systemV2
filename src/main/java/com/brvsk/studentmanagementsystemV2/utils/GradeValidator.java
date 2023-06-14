package com.brvsk.studentmanagementsystemV2.utils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class GradeValidator implements ConstraintValidator<Grade, Double> {

    @Override
    public void initialize(Grade constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Double grade, ConstraintValidatorContext constraintValidatorContext) {
        return (hasValidRange(grade) && hasValidEndingValue(grade));
    }

    private boolean hasValidRange(Double grade){
        return grade >= 2.0 && grade <= 5.0;
    }

    private boolean hasValidEndingValue(Double grade){
        double endingFractionalPart = grade % 1;
        return endingFractionalPart == 0 || endingFractionalPart == 0.5;
    }
}
