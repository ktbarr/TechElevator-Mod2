package com.techelevator.views;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

import com.techelevator.models.Product;
import com.techelevator.models.UserCredentials;

public class UserInput
{
	protected static Scanner console = new Scanner(System.in);
	
	public static int getHomeMenuOption()
	{
		System.out.println("What would you like to do?");
		System.out.println();

		System.out.println("1) Display Products");
		System.out.println("2) Select Product");
		System.out.println("3) Purchase Product");
		System.out.println("4) Login");
		System.out.println("5) Add Money");
		System.out.println("0) Exit");
		
		System.out.println();
		System.out.print("Please select an option: ");
		
		int selection = Integer.parseInt(console.nextLine());
		System.out.println();
		
		return selection;		
	}

	public static String getProductSelection()
	{
		System.out.println();
		System.out.print("Select a product: ");

		String slot = console.nextLine();
		System.out.println();
		
		return slot.toUpperCase();		
	}

	public static int getQuantity()
	{
		System.out.print("Specify the quantity: ");

		int quantity = Integer.parseInt(console.nextLine());
		System.out.println();

		return quantity;
	}

	public static UserCredentials getLoginCredentials()
	{
		System.out.println();
		System.out.print("Enter your user name: ");
		String username = console.nextLine();
		System.out.print("Enter your password: ");
		String password = console.nextLine();
		System.out.println();
		
		UserCredentials credentials = new UserCredentials(username, password);
		
		return credentials;
	}

	public static BigDecimal getDepositAmount()
	{
		System.out.println();
		System.out.print("Specify the amount of money to enter: ");
		String input = console.nextLine();
		System.out.println();
		
		BigDecimal amount = new BigDecimal(input);
		
		return amount;
	}
	
}
