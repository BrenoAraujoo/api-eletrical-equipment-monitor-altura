package com.api.exceptionhandler;


import com.api.domain.entities.exceptions.EntityInUseException;
import com.api.domain.entities.exceptions.EntityNotFoundException;
import java.time.LocalDateTime;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(EntityNotFoundException.class)
    ResponseEntity<?> handleEntityNotFound(EntityNotFoundException ex, WebRequest request) {

        HttpStatus status = HttpStatus.NOT_FOUND;
        ProblemType problemType = ProblemType.ERROR_RESOURCE_NOT_FOUND;

        Problem problem = createProblemBuilder(status,problemType,ex.getMessage()).build();
        return handleExceptionInternal(ex,problem,new HttpHeaders(),status,request);
    }

    @ExceptionHandler(EntityInUseException.class)
    ResponseEntity<?> handleEntityInUse(EntityInUseException ex, WebRequest request){
        HttpStatus status = HttpStatus.CONFLICT;
        ProblemType problemType = ProblemType.ERROR_RESOURCE_IN_USE;

        Problem problem = createProblemBuilder(status,problemType,ex.getMessage()).build();

        return handleExceptionInternal(ex,problem, new HttpHeaders(),status,request);

    }
    private Problem.ProblemBuilder createProblemBuilder(HttpStatus status, ProblemType problemType, String detail) {
        return Problem.builder()
                        .status(status.value())
                        .timestamp(LocalDateTime.now())
                        .title(problemType.getTitle())
                        .detail(detail);
    }

}
