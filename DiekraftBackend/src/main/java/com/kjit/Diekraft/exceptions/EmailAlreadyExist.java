package com.kjit.Diekraft.exceptions;

public class EmailAlreadyExist extends ErrorDetails {

    public EmailAlreadyExist(String message, Integer httpStatusCode) {
        super(message, httpStatusCode);
    }
}
