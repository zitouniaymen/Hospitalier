package com.bezkoder.spring.jpa.h2.exceptions;

public class TitleNotFoundException extends RuntimeException {
    
    private static final  String TITLE_NOT_FOUND ="Title not found";

    public TitleNotFoundException() {
        super(TITLE_NOT_FOUND);
    }

    public TitleNotFoundException(String message) {
        super(message);
    }

    public TitleNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public TitleNotFoundException(Throwable cause) {
        super(TITLE_NOT_FOUND, cause);
    }
}
