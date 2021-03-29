package com.techelevator.vendingmachine.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( value = HttpStatus.BAD_REQUEST, reason = "There are insufficient funds for the requested purchase.")
public class InsufficientFundsException extends RuntimeException
{

}
