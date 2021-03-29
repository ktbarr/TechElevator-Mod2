package com.techelevator.hotellisting.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( code = HttpStatus.NOT_FOUND, reason = "Hotel Not Found")
public class HotelNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

    public HotelNotFoundException(){
        super("Hotel Not Found");
    }
}
