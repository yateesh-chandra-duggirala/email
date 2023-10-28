package com.project.email.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class UnexpectedException extends RuntimeException {

    /**
     * This is serial version.
     */
    private static final long serialVersionUID = 1L;

    public UnexpectedException(String message) {
        super(message);
    }
}
