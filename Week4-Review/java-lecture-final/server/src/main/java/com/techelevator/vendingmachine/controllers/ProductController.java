package com.techelevator.vendingmachine.controllers;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.vendingmachine.dao.ProductDAO;
import com.techelevator.vendingmachine.dao.UserDAO;
import com.techelevator.vendingmachine.exceptions.InsufficientFundsException;
import com.techelevator.vendingmachine.models.Product;
import com.techelevator.vendingmachine.models.security.User;

@RestController
@RequestMapping(path="/products")
public class ProductController
{
	@Autowired
	ProductDAO dao;
	@Autowired
	UserDAO userDAO;
	
	@GetMapping()
	public List<Product> getAll()
	{
		List<Product> products = dao.getProducts();
		
		return products;		
	}

	@GetMapping("/slot/{id}")
	public Product get(@PathVariable String id)
	{
		Product product = dao.findBySlot(id);
		return product;
	}
	
	@GetMapping("/{id}")
    public Product get(@PathVariable int id)
    {
    	Product product = dao.get(id);    	
    	return product;
	}
	
	@PreAuthorize("isAuthenticated()")
	@PutMapping("/restock/{id}")
	public void restock(@PathVariable int id, @RequestBody Product product) 
	{
		dao.updateStock(id, product.getQuantity());
	}
	
	@PreAuthorize("isAuthenticated()")
	@PutMapping("/purchase/{id}")
	public void purchase(@PathVariable int id, Principal principal)
	{
		User user = userDAO.findByUsername(principal.getName());
		
		Product product = dao.get(id);
		BigDecimal newBalance = user.getBalance().subtract(product.getPrice());
		
		if(newBalance.compareTo(BigDecimal.ZERO) < 0)
		{
			throw new InsufficientFundsException();
		}
		
		userDAO.purchase(user.getId(), product.getPrice());
		dao.purchase(id);
	}
}
