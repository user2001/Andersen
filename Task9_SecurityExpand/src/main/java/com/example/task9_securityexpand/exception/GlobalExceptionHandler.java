package com.example.task9_securityexpand.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public Map<String, Object> handleAccessDeniedException(AccessDeniedException ex) {
        return getResponse(HttpStatus.FORBIDDEN.toString(), ex.getMessage());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public Map<String, Object> handleBaCredentialsException(BadCredentialsException ex) {
        return getResponse(HttpStatus.FORBIDDEN.toString(), ex.getMessage());
    }

    private Map<String, Object> getResponse(String status, String message) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", status);
        response.put("message", message);
        return response;
    }

    @ExceptionHandler
    public Map<String, Object> handleEntityNotFoundException(EntityNotFoundException ex) {
        return getResponse(HttpStatus.BAD_REQUEST.toString(), ex.getMessage());
    }

}
