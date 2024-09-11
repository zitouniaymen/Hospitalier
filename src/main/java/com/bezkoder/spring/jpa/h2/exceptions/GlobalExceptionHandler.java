package com.bezkoder.spring.jpa.h2.exceptions;

;
import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import java.nio.file.AccessDeniedException;
import java.security.SignatureException;

@ControllerAdvice
public class GlobalExceptionHandler {


    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> IHandlerllegalArgumentException(IllegalArgumentException e){
        LOGGER.warn("Bad request, {}", e.getMessage());
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> IHandlerException(Exception e){
        LOGGER.error("Error with pasing request, {}", e.getMessage());
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(TutorialNotFoundException.class)
    public ResponseEntity<String> HandlerTutorialNotFoundExceptionException(TutorialNotFoundException e){

        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

   

}


