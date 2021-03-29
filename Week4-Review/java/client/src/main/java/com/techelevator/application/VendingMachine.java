package com.techelevator.application;

import java.util.ArrayList;
import java.util.List;

import com.techelevator.models.Product;
import com.techelevator.services.AuthenticationService;
import com.techelevator.services.ProductService;
import com.techelevator.views.UserInput;
import com.techelevator.views.UserOutput;

public class VendingMachine
{
    AuthenticationService authenticationService;
    ProductService productService;
    
    public VendingMachine(String baseUrl)
    {
    	authenticationService = new AuthenticationService(baseUrl);
    	productService = new ProductService(baseUrl);
    }

    public void run()
    {
    	 
    	 UserOutput.displayWelcome();
    	
         while (true) 
         {             
             // Ensure loop continues until the user quits the application
        	 int menuSelection = UserInput.getHomeMenuOption();
        	 
        	 if(menuSelection == 1) 
        	 {
        		 // display all products
        		 displayProducts();
        		 System.out.println();
        	 }
        	 else if(menuSelection == 2) 
        	 {
        		 selectProduct();
        	 }
        	 else if(menuSelection == 3) 
        	 {
        		 
        	 }
        	 else if (menuSelection == 0)
        	 {
        		 break;
        	 }
        	 else 
        	 {
        		 // invalid option
        		 System.out.println("Please select a valid option");
        	 }
        	  
         }
         
         System.out.println("Goodbye");
    }
    
    private void displayProducts() 
    {
    	// TODO: get a list of products from the API
    	List<Product> products = productService.getAllProducts();
    	
    	UserOutput.displayProducts(products);
    }
    
    private void selectProduct() 
    {
    	List<Product> products = productService.getAllProducts();
    	
    	UserOutput.displayProducts(products);
    	
    	// get selection from User
    	
    	int productId = UserInput.getProductSelection(products);
    	
    	Product selectedProduct = productService.getProductById(productId);
    	
    	UserOutput.displayProductDeatils(selectedProduct);
    	
    }
    
}
