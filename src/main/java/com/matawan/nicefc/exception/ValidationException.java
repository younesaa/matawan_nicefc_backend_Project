package com.matawan.nicefc.exception;

import java.util.List;


/**
 * Exception thrown when attempting to register a team with constraints violations.
 * Extends RuntimeException to indicate a runtime exception scenario.
 */
public class ValidationException extends RuntimeException{
    private final List<String> errors;

    /**
     * Constructs a new ValidationException with the specified error message and list of errors.
     *
     * @param message The error message indicating that a team object has violations.
     *
     * @param errors list of violations.
     */
    public ValidationException(String message, List<String> errors) {
        super(message);
        this.errors = errors;
    }

    /**
     * Retrieves the error message associated with this exception.
     *
     * @return The error message.
     */
    public List<String> getErrors() {
        return errors;
    }
}
