package com.javacourse.exceptions;

/**
 * This exception appears as a higher layer db exception when it is impossible
 * to get a connection from the connection pool
 */
public class NoConnectionToDbException extends RuntimeException{
    public NoConnectionToDbException() {
        super();
    }

    public NoConnectionToDbException(String message) {
        super(message);
    }

    public NoConnectionToDbException(String message, Throwable cause) {
        super(message, cause);
    }
}
