package com.techelevator.hotellisting.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * HotelNotFoundException
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class HotelNotFoundException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1834874773488347383L;

    public HotelNotFoundException() {
    }

    public HotelNotFoundException(String message) {
        super(message);
    }

    public HotelNotFoundException(Throwable cause) {
        super(cause);
    }

    public HotelNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public HotelNotFoundException(String message, Throwable cause,
            boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }


}