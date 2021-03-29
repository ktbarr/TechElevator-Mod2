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
        	 int menuSelection = UserInput.getHomeMenuOption();  
        	 
        	 if(menuSelection == 1)
        	 {
        		 // display all products
        		 displayProducts();
        	 }
        	 else if(menuSelection == 2)
        	 {
        		 selectProduct();
        	 }
        	 else if(menuSelection == 0)
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
    	List<Product> products = productService.getAllProducts();    	
    	UserOutput.displayProducts(products);
    	
    	// get selection from user
    	int productId = UserInput.getProductSelection(products);
    	
    	// call the api to get the product
    	Product selectedProduct = productService.getProductById(productId);

    	// display the product details of the selected product
    	UserOutput.displayProductDetails(selectedProduct);
    }
    
}
