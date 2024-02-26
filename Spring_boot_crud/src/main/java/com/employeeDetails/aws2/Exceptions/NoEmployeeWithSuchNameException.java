package com.employeeDetails.aws2.Exceptions;

public class NoEmployeeWithSuchNameException extends Exception{

    String message;
    public NoEmployeeWithSuchNameException(String message)
    {
        super(message);
    }

}
