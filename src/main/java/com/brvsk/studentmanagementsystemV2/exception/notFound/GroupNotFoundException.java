package com.brvsk.studentmanagementsystemV2.exception.notFound;

public class GroupNotFoundException extends NotFoundException {
    public GroupNotFoundException(final Long courseId ){
        super("Course with id "+courseId+" not found");
    }
}
