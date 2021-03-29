package com.techelevator.models;

import java.math.BigDecimal;

public class Product
{
	private int id;
	private String name;
	private BigDecimal price;
	private String type;
	private String slot;
	private int quantity;
	
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public BigDecimal getPrice()
	{
		return price;
	}
	public void setPrice(BigDecimal price)
	{
		this.price = price;
	}
	public String getType()
	{
		return type;
	}
	public void setType(String type)
	{
		this.type = type;
	}
	public String getSlot()
	{
		return slot;
	}
	public void setSlot(String slot)
	{
		this.slot = slot;
	}
	public int getQuantity()
	{
		return quantity;
	}
	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}
	
}
