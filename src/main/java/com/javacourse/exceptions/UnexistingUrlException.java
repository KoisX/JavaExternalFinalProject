package com.javacourse.exceptions;

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
