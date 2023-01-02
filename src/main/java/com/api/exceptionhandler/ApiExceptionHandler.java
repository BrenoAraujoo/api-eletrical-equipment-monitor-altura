package com.api.exceptionhandler;


import com.api.domain.entities.exceptions.EntityInUseException;
import com.api.domain.entities.exceptions.EntityNotFoundException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {


    public static final String MSG_GENERIC_USER_MESSAGE = "Internal server error. Try again, if the problem persists, contact your system administrator";
    public static final String MSG_INVALID_FORMAT =
            "The property '%s' received the value '%s' which is of an invalid type. Correct and enter a value compatible with '%s'";

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleUncaught(Exception ex, WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String detail = MSG_GENERIC_USER_MESSAGE;
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
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        if (body == null)
            body = Problem.builder()
                    .timestamp(LocalDateTime.now())
                    .title(status.getReasonPhrase())
                    .userMessage(MSG_GENERIC_USER_MESSAGE)
                    .status(status.value())
                    .build();
        /*
        All methods from ResponseEntityExceptionHandler that are instance of String
         */
        else if (body instanceof String)
            body = Problem.builder()
                    .timestamp(LocalDateTime.now())
                    .title((String) body)
                    .userMessage(MSG_GENERIC_USER_MESSAGE)
                    .status(status.value())
                    .build();

        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    @Override
    public ResponseEntity<Object> handleHttpMessageNotReadable
            (HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

//        Throwable rootCause = ex.getRootCause();
        Throwable rootCause = ExceptionUtils.getRootCause(ex);

        if (rootCause instanceof InvalidFormatException) {
            return handleInvalidFormat((InvalidFormatException) rootCause, status, headers, request);
        } else if (rootCause instanceof PropertyBindingException) {
            return handlePropertyBinding((PropertyBindingException) rootCause, new HttpHeaders(), status, request);
        }

        ProblemType problemType = ProblemType.ERROR_ILLEGIBLE_MESSAGE;
        String detail = "The request body is invalid. Check syntax error.";
        Problem problem = createProblemBuilder(status, problemType, detail).build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);

    }

    public ResponseEntity<Object> handlePropertyBinding
            (PropertyBindingException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        String path = joinPath(ex);
        String detail = String.format("The property '%s' doesn't exists. Correct and try again.",path);
        System.out.println(detail);
        ProblemType problemType = ProblemType.ERROR_INVALID_DATA;
        Problem problem = createProblemBuilder(status, problemType, detail)
                .userMessage(MSG_GENERIC_USER_MESSAGE)
                .build();

        return handleExceptionInternal(ex,problem,new HttpHeaders(),status,request);
    }

    public ResponseEntity<Object> handleInvalidFormat(InvalidFormatException ex, HttpStatus status, HttpHeaders headers, WebRequest request) {

        String path = joinPath(ex);

        ProblemType problemType = ProblemType.ERROR_ILLEGIBLE_MESSAGE;
        String detail = String.format(MSG_INVALID_FORMAT, path, ex.getValue(), ex.getTargetType().getSimpleName());
        Problem problem = createProblemBuilder(status, problemType, detail).build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);

    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

//        BindingResult bindingResult = ex.getBindingResult();

        var objectError = joinErrors(ex.getBindingResult());

        return ResponseEntity.ok(objectError);
    }

    private List<Problem.Object> joinErrors(BindingResult bindingResult){

        var objectError = bindingResult.getAllErrors()
                .stream()
                .map(obj -> {
                        String message = obj.getDefaultMessage();
                        String name =obj.getObjectName();

                        return Problem.Object.builder()
                                .userMessage(message)
                                .name(name)
                                .build();
                }).toList();

        return objectError;
    }

    private String joinPath(JsonMappingException ex) {

        var references = ex.getPath();
        System.out.println(references);
        return references.stream()
                .map(JsonMappingException.Reference::getFieldName)
                .collect(Collectors.joining("."));
    }

    private String joinErrors(List<Problem.Object> list) {
        return list.stream()
                .map(Problem.Object::getName)
                .collect(Collectors.joining(" e "));
    }


    private Problem.ProblemBuilder createProblemBuilder(HttpStatus status, ProblemType problemType, String detail) {
        return Problem.builder()
                .status(status.value())
                .timestamp(LocalDateTime.now())
                .title(problemType.getTitle())
                .detail(detail);
    }

}
