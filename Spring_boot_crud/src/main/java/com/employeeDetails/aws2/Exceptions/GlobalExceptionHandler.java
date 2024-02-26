package com.employeeDetails.aws2.Exceptions;

import jakarta.validation.ConstraintViolationException;
import lombok.experimental.StandardException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler(IllegalArgumentException.class)
//       public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex)
//       {
//           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
//       }

       @ExceptionHandler(ConstraintViolationException.class)
       @ResponseBody
       public Map<String,String> handleMethodArgumentNotValid(ConstraintViolationException ex) {
           Map<String, String> errors = new HashMap<>();
           ex.getConstraintViolations().forEach((error) -> {
               String fieldName = error.getPropertyPath().toString();
               String errorMessage = error.getMessage();
               errors.put(fieldName, errorMessage);
           });
           return errors;
       }

       @ExceptionHandler(HandleAllException.class)
       public ResponseEntity<String> handleHandleAllException(HandleAllException ex)
       {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
       }

       @ExceptionHandler(NoEmployeeWithSuchNameException.class)
       public ResponseEntity<String> handleNoEmployeeWithSuchNameException(NoEmployeeWithSuchNameException ex)
       {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
       }

       @ExceptionHandler(NoSuchEmailFoundException.class)
       public ResponseEntity<String> handleNoSuchEmailFoundException(NoSuchEmailFoundException ex)
       {
           return ResponseEntity.status(HttpStatus.OK).body(ex.getMessage());
       }
}
