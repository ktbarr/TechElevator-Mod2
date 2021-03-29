package com.techelevator.hotellisting.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * ReviewNotFoundException
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ReviewNotFoundException extends Exception {
    /**
    *
    */
    private static final long serialVersionUID = 3088973276160460191L;

    public ReviewNotFoundException() {
    }

    public ReviewNotFoundException(String message) {
        super(message);
    }

    public ReviewNotFoundException(Throwable cause) {
        super(cause);
    }

    public ReviewNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReviewNotFoundException(String message, Throwable cause,
            boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }


}