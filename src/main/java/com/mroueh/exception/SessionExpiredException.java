package com.mroueh.exception;

public class SessionExpiredException extends RuntimeException{

    public SessionExpiredException(String message) {
        super(message);
    }
}
