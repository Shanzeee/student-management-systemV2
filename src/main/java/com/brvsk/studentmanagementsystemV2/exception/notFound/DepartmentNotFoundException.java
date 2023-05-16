package com.brvsk.studentmanagementsystemV2.exception.notFound;

public class DepartmentNotFoundException extends NotFoundException {

    public DepartmentNotFoundException(final Long departmentId){
        super("Department with id " +departmentId+ " not found");
    }
}
