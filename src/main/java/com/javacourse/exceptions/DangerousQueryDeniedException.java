package com.javacourse.exceptions;

/**
 * This exception is supposed to be thrown from DAO class's
 * methods, which are considered highly unsafe and are forbidden to be called
 */
public class DangerousQueryDeniedException extends RuntimeException{
    public DangerousQueryDeniedException() {
    }

    public DangerousQueryDeniedException(String message) {
        super(message);
    }

    public DangerousQueryDeniedException(String message, Throwable cause) {
        super(message, cause);
    }
}
