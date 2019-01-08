package com.javacourse.exceptions;

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
