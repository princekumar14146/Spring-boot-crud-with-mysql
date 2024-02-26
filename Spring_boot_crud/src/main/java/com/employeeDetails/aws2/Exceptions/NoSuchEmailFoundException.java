package com.employeeDetails.aws2.Exceptions;

public class NoSuchEmailFoundException extends Exception {

    String message;

    public NoSuchEmailFoundException(String message)
    {
        super(message);
    }



}
