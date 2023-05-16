package com.brvsk.studentmanagementsystemV2.exception.notFound;

public class TeacherNotFoundException extends NotFoundException{

    public TeacherNotFoundException(final Long teacherId){
        super("Teacher with id " +teacherId+ " not found");
    }
}


