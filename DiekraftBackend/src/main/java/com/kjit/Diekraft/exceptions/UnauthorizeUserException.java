package com.kjit.Diekraft.exceptions;

public class UnauthorizeUserException extends ErrorDetails {
    public UnauthorizeUserException(String message, Integer httpStatusCode) {
        super(message, httpStatusCode);
    }
}
