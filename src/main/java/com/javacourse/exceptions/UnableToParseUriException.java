package com.javacourse.exceptions;

/**
 * Is thrown when uri can't be parsed in UriMarshaller
 */
public class UnableToParseUriException extends RuntimeException{
    public UnableToParseUriException() {
        super();
    }

    public UnableToParseUriException(String message) {
        super(message);
    }

    public UnableToParseUriException(String message, Throwable cause) {
        super(message, cause);
    }
}
