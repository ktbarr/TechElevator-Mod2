package com.techelevator.vendingmachine.dao;

import java.util.List;

import com.techelevator.vendingmachine.models.Product;

public interface ProductDAO
{
	List<Product> getProducts();
	Product get(int id);
	Product findBySlot(String slotId);
	void updateStock(int id, int quantity);
	void purchase(int id);
}
