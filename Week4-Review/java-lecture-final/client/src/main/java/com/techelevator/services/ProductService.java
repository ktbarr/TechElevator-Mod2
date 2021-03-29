package com.techelevator.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.techelevator.models.AuthenticatedUser;
import com.techelevator.models.Product;

public class ProductService extends ApiServiceBase
{
	public ProductService(String url)
	{
		super(url);
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
		Product product = restTemplate.getForObject(url, Product.class);
		
		return product;		
	}

	public Product getBySlotId(String slotId)
	{
		String url = BASE_URL + "/slot/" + slotId;
		Product product = restTemplate.getForObject(url, Product.class);

		return product;
	}

	public void purchase(Product product)
	{
		String url = BASE_URL + "/purchase/" + product.getId();

		HttpEntity<Product> entity = makeEntity(product);
		restTemplate.put(url, entity);

	}

	public void restock(Product product)
	{
		String url = BASE_URL + "/restock/" + product.getId();

		HttpEntity<Product> entity = makeEntity(product);
		restTemplate.put(url, entity);

	}
	
	private HttpEntity<Product> makeEntity(Product product)
	{
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		// add bearer token if user is logged in
		if(user != null)
		{
			headers.setBearerAuth(user.getToken());
		}
		
		HttpEntity<Product> entity = new HttpEntity<Product>(product, headers);
		
		return entity;
	}
}
