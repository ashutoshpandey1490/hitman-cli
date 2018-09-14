package com.assignment.hitman.exception;

/** @author ashutoshp */
public class PlayerAlreadyExistException extends Exception {

    private static final long serialVersionUID = 8156669723722341237L;

    public PlayerAlreadyExistException(String message) {
        super(message);
    }

    public PlayerAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
