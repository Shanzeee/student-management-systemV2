package com.brvsk.studentmanagementsystemV2.exception.notFound;


public class CourseNotFoundException extends NotFoundException{

    public CourseNotFoundException(final Long courseId){
        super("Course with id " +courseId+ " not found");
    }
}
