package com.matawan.nicefc.exception;

/**
 * Exception thrown when attempting to register a team with a name that already exists.
 * Extends RuntimeException to indicate a runtime exception scenario.
 */
public class teamAlreadyExistsException extends RuntimeException{

    private final String message;

    /**
     * Constructs a new TeamAlreadyExistsException with the specified error message.
     *
     * @param message The error message indicating that a team with the same name already exists.
     */
    public teamAlreadyExistsException(String message) {
        super(message);
        this.message=message;
    }

    /**
     * Retrieves the error message associated with this exception.
     *
     * @return The error message.
     */
    @Override
    public String getMessage() {
        return message;
    }
}
