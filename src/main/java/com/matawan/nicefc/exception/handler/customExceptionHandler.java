package com.matawan.nicefc.exception.handler;

import com.matawan.nicefc.exception.ValidationException;
import com.matawan.nicefc.exception.teamAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

/**
 * Global exception handler for handling custom exceptions in the customer-related controllers.
 * Extends ResponseEntityExceptionHandler to provide custom responses for certain exceptions.
 */
@ControllerAdvice
public class customExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handles ValidationException thrown during validation of input data.
     *
     * @param ex The ValidationException containing validation errors.
     * @return ResponseEntity with a list of validation errors and HTTP status code 400 (Bad Request).
     */
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Object> handleValidationException(ValidationException ex){
        List<String> errors = ex.getErrors();
        return ResponseEntity.status(HttpStatus.FOUND).body(errors);
    }

    /**
     * Handles teamAlreadyExistsException thrown when attempting to register a team with a name that already exists.
     *
     * @param ex The teamAlreadyExistsException containing an error message.
     * @return ResponseEntity with the error message and HTTP status code 302 (Found).
     */
    @ExceptionHandler(teamAlreadyExistsException.class)
    public ResponseEntity<Object> handleTeamAlreadyExistsException(teamAlreadyExistsException ex){
        String error = ex.getMessage();
        return ResponseEntity.status(HttpStatus.FOUND).body(error);
    }
}
