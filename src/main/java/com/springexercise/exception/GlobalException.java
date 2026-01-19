package com.springexercise.exception;

import com.springexercise.common.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(DuplicateException.class)
    public ResponseEntity<Response> handleDuplicateException(DuplicateException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(Response.error("409" , "fail" , e.getMessage()));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Response> handleResourceNotFoundException(ResourceNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Response.error("404" , "fail" , e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> handleGenericException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Response.error("500" , "fail" , e.getMessage()));
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response> handleValidationException(MethodArgumentNotValidException e) {
        Map<String,String> errors = new HashMap();

        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();

            // insert into errors map
            errors.put(fieldName,message);
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Response.error("501","fail","validation failed",errors));
    }
}
