package com.techelevator.views;

import java.math.BigDecimal;
import java.util.List;

import com.techelevator.models.Product;
import com.techelevator.models.User;

public class UserOutput
{
	public static void displayWelcome()
	{
		System.out.println("Welcome to The Vendor");
		System.out.println();
	}
	
	public static void displayProducts(List<Product> products)
	{
		System.out.println();
		System.out.println("---------- Products -----------");
		
		for (Product product : products)
		{
			System.out.println(product.getSlot() + "   " + product.getName() + " $" + product.getPrice() + " - " + product.getQuantity());
		}

		System.out.println();
	}
	
	public static void displayProductDetails(Product product)
	{
		System.out.println();
		System.out.println("---------- Selected Product -----------");
		System.out.println();

		System.out.println("Slot: " + product.getSlot());
		System.out.println("Id: " + product.getId());
		System.out.println("Name: " + product.getName());
		System.out.println("Price: $" + product.getPrice());
		System.out.println("Type: " + product.getType());
		System.out.println("Quantity: " + product.getQuantity());
		
		System.out.println();
		
	}

	public static void displayMessage(String message)
	{
		System.out.println();
		System.out.println(message);
		System.out.println();
	}

	public static void displayDepositResult(BigDecimal amount, User user)
	{
		System.out.println();
		System.out.println("$" + amount + " has been added to your account.");
		System.out.println("Current Balance: $" + user.getBalance());
		System.out.println();
	}

}
