package com.techelevator.vendingmachine.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( value = HttpStatus.NOT_FOUND, reason = "The product does not exist.")
public class ProductNotFoundException extends RuntimeException
{

}
