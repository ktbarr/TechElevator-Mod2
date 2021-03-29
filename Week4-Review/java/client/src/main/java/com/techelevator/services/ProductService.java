package com.techelevator.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.techelevator.models.Product;

public class ProductService
{

	private String BASE_URL;
	private RestTemplate restTemplate = new RestTemplate();

	public ProductService(String url)
	{
		this.BASE_URL = url + "products";
	}
	
	public List<Product> getAllProducts() 
	{
		List<Product> products;
		Product[] productsArray = restTemplate.getForObject(BASE_URL, Product[].class);
		products = Arrays.asList(productsArray);
		
		return products;
	}
	
	public Product getProductById(int id) 
	{
		String url = BASE_URL + "/" + id;
		Product product = restTemplate.getForObject(BASE_URL, Product.class);
		
		return product;
	}
}
