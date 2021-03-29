package com.techelevator.models;

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
	
	public Deposit(BigDecimal amount)
	{
		this.amount = amount;
	}
}
