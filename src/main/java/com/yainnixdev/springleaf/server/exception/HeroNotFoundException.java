package com.yainnixdev.springleaf.server.exception;

public final class HeroNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1430758394076072752L;


    @Override
    public Throwable fillInStackTrace() {
        return this;
    }


    public HeroNotFoundException(final String message) {
        super(message);
    }


}