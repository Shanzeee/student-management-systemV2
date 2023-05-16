package com.brvsk.studentmanagementsystemV2.exception.notFound;

public class ExamNotFoundException extends NotFoundException {
    public ExamNotFoundException(final Long examId) {
        super("Exam with id " +examId+ " not found");
    }
}
