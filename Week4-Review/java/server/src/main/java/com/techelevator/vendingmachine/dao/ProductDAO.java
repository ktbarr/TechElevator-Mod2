package com.techelevator.vendingmachine.dao;

import java.util.List;

import com.techelevator.vendingmachine.models.Product;

public interface ProductDAO
{

	List<Product> getProducts();
	Product get(int id);
}
