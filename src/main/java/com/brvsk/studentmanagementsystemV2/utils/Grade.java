package com.brvsk.studentmanagementsystemV2.utils;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = GradeValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Grade {
    String message() default "Grade is not valid. It should be in range 2.0 - 5.0 and ending value should be X.0 or X.5";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
