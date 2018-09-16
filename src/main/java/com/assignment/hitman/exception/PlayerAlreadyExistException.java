package com.assignment.hitman.exception;

/**
 * This exception is thrown when player with the same name is being created.
 *
 * @author ashutoshp
 */
public class PlayerAlreadyExistException extends Exception {

    private static final long serialVersionUID = 8156669723722341237L;

    public PlayerAlreadyExistException(String message) {
        super(message);
    }

    public PlayerAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
