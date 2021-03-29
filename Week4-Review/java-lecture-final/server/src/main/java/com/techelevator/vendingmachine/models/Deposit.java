package com.techelevator.vendingmachine.models;

import java.math.BigDecimal;

public class Deposit 
{
    private BigDecimal amount;

	public BigDecimal getAmount()
	{
		return amount;
	}
	public void setAmount(BigDecimal amount)
	{
		this.amount = amount;
	}

}
