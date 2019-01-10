package com.javacourse.exceptions;

/**
 * This exception is thrown when some problem occurs during URL parsing
 */
public class UnexistingUrlException extends RuntimeException{
    public UnexistingUrlException() {
    }

    public UnexistingUrlException(String message) {
        super(message);
    }

    public UnexistingUrlException(String message, Throwable cause) {
        super(message, cause);
    }
}
