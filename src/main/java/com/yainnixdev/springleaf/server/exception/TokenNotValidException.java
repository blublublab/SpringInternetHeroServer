package com.yainnixdev.springleaf.server.exception;

public final class TokenNotValidException extends RuntimeException {

    private static final long serialVersionUID = 1430751388076072752L;


    @Override
    public Throwable fillInStackTrace() {
        return this;
    }


    public TokenNotValidException(final String message) {
        super(message);
    }


}