package com.brvsk.studentmanagementsystemV2.exception;

public class StudentNotAssignedToCourseException extends IllegalStateException {

    public StudentNotAssignedToCourseException(Long studentId, Long courseId) {
        super("Student with id "+studentId+" is not assigned to course with id "+courseId);
    }
}
