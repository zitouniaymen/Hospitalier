package com.bezkoder.spring.jpa.h2.exceptions;

public class TutorialNotFoundException extends RuntimeException {

    private static final  String TITLE_NOT_FOUND ="Title not found";

    public TutorialNotFoundException() {
        super(TITLE_NOT_FOUND);
    }

    public TutorialNotFoundException(String message) {
        super(message);
    }

    public TutorialNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public TutorialNotFoundException(Throwable cause) {
        super(TITLE_NOT_FOUND, cause);
    }
}
