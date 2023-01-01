package com.api.exceptionhandler;


import com.api.domain.entities.exceptions.EntityInUseException;
import com.api.domain.entities.exceptions.EntityNotFoundException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {


    public static final String MSG_INTERNAL_SERVER_ERROR = "Internal server error. Try again, if the problem persists, contact your system administrator";

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleUncaught(Exception ex, WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String detail = MSG_INTERNAL_SERVER_ERROR;
        ProblemType problemType = ProblemType.ERROR_ILLEGIBLE_MESSAGE;

        Problem problem = createProblemBuilder(status, problemType, detail).build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFound(EntityNotFoundException ex, WebRequest request) {

        HttpStatus status = HttpStatus.NOT_FOUND;
        ProblemType problemType = ProblemType.ERROR_RESOURCE_NOT_FOUND;

        Problem problem = createProblemBuilder(status, problemType, ex.getMessage()).build();
        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EntityInUseException.class)
    public ResponseEntity<?> handleEntityInUse(EntityInUseException ex, WebRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;
        ProblemType problemType = ProblemType.ERROR_RESOURCE_IN_USE;

        Problem problem = createProblemBuilder(status, problemType, ex.getMessage()).build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);

    }


    @Override
    public ResponseEntity<Object> handleHttpMessageNotReadable
            (HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

//        Throwable rootCause = ex.getRootCause();
        Throwable rootCause = ExceptionUtils.getRootCause(ex);

        if (rootCause instanceof InvalidFormatException) {
            return handleInvalidFormatException((InvalidFormatException) rootCause,status,headers,request);
        } else if (rootCause instanceof PropertyBindingException) {
            return ResponseEntity.ok("Property biding exception");
        }

        ProblemType problemType = ProblemType.ERROR_ILLEGIBLE_MESSAGE;
        String detail = "The request body is invalid. Check syntax error.";
        Problem problem = createProblemBuilder(status, problemType, detail).build();


        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);

    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

//        if(body == null){
//            body = Problem.builder()
//                    .timestamp(LocalDateTime.now())
//                    .userMessage(status.getReasonPhrase())
//                    .detail(MSG_INTERNAL_SERVER_ERROR)
//                    .status(status.value())
//                    .build();
//        }


        if(body == null){
            body = "Corpo dessa exception é null";
        }

        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    public ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException ex, HttpStatus  status, HttpHeaders headers, WebRequest request){

        String path = joinPath(ex);

        ProblemType problemType = ProblemType.ERROR_ILLEGIBLE_MESSAGE;
        String detail =String.format("The property received the value '%s' which is of an invalid type. Correct and enter a value compatible with '%s'","valor1","valor2)");
        Problem problem = createProblemBuilder(status,problemType,detail).build();

        return handleExceptionInternal(ex,problem,new HttpHeaders(),status,request);
    }

    private String joinPath(JsonMappingException ex) {

        var references = ex.getPath();

        return references.stream()
                .map(JsonMappingException.Reference::getFieldName)
                .collect(Collectors.joining("."));

    }


    private Problem.ProblemBuilder createProblemBuilder(HttpStatus status, ProblemType problemType, String detail) {
        return Problem.builder()
                .status(status.value())
                .timestamp(LocalDateTime.now())
                .title(problemType.getTitle())
                .detail(detail);
    }

}
