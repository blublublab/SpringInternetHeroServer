package com.yainnixdev.springleaf.server.exception;

public final class UserAlreadyExistException extends RuntimeException {

    private static final long serialVersionUID = 1430751398076078467L;


    @Override
    public Throwable fillInStackTrace() {
        return this;
    }


    public UserAlreadyExistException(final String message) {
        super(message);
    }


}