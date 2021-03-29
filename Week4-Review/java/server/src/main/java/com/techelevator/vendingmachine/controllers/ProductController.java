package com.techelevator.vendingmachine.controllers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.vendingmachine.dao.ProductDAO;
import com.techelevator.vendingmachine.models.Product;

@RestController
public class ProductController
{
	//@RequestMapping(path = "/products", method = RequestMethod.GET) this is the same as below, just longer
	
	ProductDAO dao;
	
	public ProductController(ProductDAO dao)
	{
		this.dao = dao;
	}
	
	@GetMapping("/products")
	public List<Product> getAll() 
	{
		List<Product> products = dao.getProducts();
		
		return products;
	}
	
	@GetMapping("/products/{id}")
    public Product get(@PathVariable int id)
    {
		
		Product product = dao.get(id);
		
		return product;
		
 //   	Product product = new Product();
    	
//    	product.setId(id);
//    	product.setName("Sunfried crisps");
//    	product.setPrice(new BigDecimal(1.25));
//    	product.setQuantity(5);
//    	product.setSlot("A1");
//    	product.setType("Chips");
    	
    	
    }
}
