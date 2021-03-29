package com.techelevator.views;

import java.util.List;

import com.techelevator.models.Product;

public class UserOutput
{
	public static void displayWelcome()
	{
		System.out.println("Welcome to The Vending Machine");
		System.out.println();
	}
	
	public static void displayProducts(List<Product> products) 
	{
		System.out.println();
		System.out.println("---------- Products ----------");
		
		for (Product product : products)
		{
			System.out.println(product.getSlot() + " " + product.getName() + " $" + product.getPrice());
		}
		
		System.out.println();
	}
	
	public static void displayProductDeatils(Product product) 
	{
		System.out.println();
		System.out.println("---------- Selected Productd ----------");
		System.out.println();
		
		System.out.println("Slot: " + product.getSlot());
		System.out.println("Id: " + product.getId());
		System.out.println("Name: " + product.getName());
		System.out.println("Price: $" + product.getPrice());
		System.out.println("Type: " + product.getType());
		
	}

}
