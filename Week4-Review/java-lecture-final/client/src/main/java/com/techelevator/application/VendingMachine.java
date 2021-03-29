package com.techelevator.application;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.techelevator.exceptions.AuthenticationServiceException;
import com.techelevator.models.AuthenticatedUser;
import com.techelevator.models.Product;
import com.techelevator.models.User;
import com.techelevator.models.UserCredentials;
import com.techelevator.services.ApiServiceBase;
import com.techelevator.services.AuthenticationService;
import com.techelevator.services.ProductService;
import com.techelevator.services.UserService;
import com.techelevator.views.UserInput;
import com.techelevator.views.UserOutput;

public class VendingMachine
{
	AuthenticationService authenticationService;
	ProductService productService;
	UserService userService;

	public VendingMachine(String baseUrl)
	{
		authenticationService = new AuthenticationService(baseUrl);
		productService = new ProductService(baseUrl);
		userService = new UserService(baseUrl);
	}

	public void run()
	{

		UserOutput.displayWelcome();

		while (true)
		{
			try
			{

				int menuSelection = UserInput.getHomeMenuOption();

				if (menuSelection == 1)
				{
					// display all products
					displayProducts();
				}
				else if (menuSelection == 2)
				{
					// display product details
					selectProduct();
				}
				else if (menuSelection == 3)
				{
					// purchase product
					purchaseProduct();
				}
				else if (menuSelection == 4)
				{
					// login
					login();
				}
				else if (menuSelection == 5)
				{
					// add money
					addMoney();
				}
				else if (menuSelection == 10)
				{
					// restock - hidden menu
					restockProduct();
				}
				else if (menuSelection == 0)
				{
					// exit
					break;
				}
				else
				{
					// invalid selection
					System.out.println("Please select a valid option");
				}

			}
			catch (Exception e)
			{
				UserOutput.displayMessage("There was an error processing your request.");
			}
		}

		System.out.println("goodbye");

	}

	private void displayProducts()
	{
		// TODO: get a list of products from the api
		List<Product> products = productService.getAllProducts();

		UserOutput.displayProducts(products);
	}

	private void selectProduct()
	{
		displayProducts();

		// get selection from user
		String slotId = UserInput.getProductSelection();

		// call the api to get the product
		Product selectedProduct = productService.getBySlotId(slotId);

		// display the product details of the selected product
		UserOutput.displayProductDetails(selectedProduct);
	}

	private void purchaseProduct()
	{
		UserOutput.displayMessage("*********** Purchase ***********");
		displayProducts();
		
		String slotId = UserInput.getProductSelection();
		Product selectedProduct = productService.getBySlotId(slotId);
		
		productService.purchase(selectedProduct);
		
		UserOutput.displayMessage(selectedProduct.getName() + " has been purchased.");
	}

	private void login() throws AuthenticationServiceException
	{
		UserCredentials credentials = UserInput.getLoginCredentials();
		AuthenticatedUser user = authenticationService.login(credentials);
		
		ApiServiceBase.setUser(user);
		
		UserOutput.displayMessage("Welcome " + user.getUser().getUsername());
	}

	private void addMoney()
	{
		UserOutput.displayMessage("*********** Add Money ***********");
		BigDecimal amount = UserInput.getDepositAmount();
		User user = userService.addMoney(amount);	
		
		UserOutput.displayDepositResult(amount, user);
	}

	private void restockProduct()
	{
		UserOutput.displayMessage("*********** Restock ***********");
		displayProducts();

		String slotId = UserInput.getProductSelection();
		int quantity = UserInput.getQuantity();

		Product selectedProduct = productService.getBySlotId(slotId);
		selectedProduct.setQuantity(quantity);

		productService.restock(selectedProduct);
		
		UserOutput.displayMessage(selectedProduct.getName() + " has been restocked.");
	}

}
