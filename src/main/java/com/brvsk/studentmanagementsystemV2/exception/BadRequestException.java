package com.brvsk.studentmanagementsystemV2.exception;

public class BadRequestException extends RuntimeException{
    public BadRequestException(final String message) {
        super(message);
    }
}
