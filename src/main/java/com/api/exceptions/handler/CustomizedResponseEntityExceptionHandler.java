package com.api.exceptions.handler;

import com.api.exceptions.ResourceNotFoundException;
import com.api.exceptions.StandardError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.concurrent.ExecutionException;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<StandardError> handleAllExceptions(Exception ex, WebRequest request){
    StandardError standardError = new StandardError(new Date(),ex.getMessage(),request.getDescription(false));
    return new ResponseEntity<>(standardError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<StandardError> handleResourceNotFoundException(Exception ex, WebRequest request){
        StandardError standardError = new StandardError(new Date(),ex.getMessage(),request.getDescription(false));
        return new ResponseEntity<>(standardError, HttpStatus.NOT_FOUND);
    }



}
