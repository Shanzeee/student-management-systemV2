package com.brvsk.studentmanagementsystemV2.exception.notFound;

public class StudentNotFoundException extends NotFoundException {
    public StudentNotFoundException(final Long id) {
        super("Student with id "+ id+ "not found");
    }
}
