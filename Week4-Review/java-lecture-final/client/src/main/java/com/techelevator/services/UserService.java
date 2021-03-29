package com.techelevator.services;

import java.math.BigDecimal;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import com.techelevator.models.Deposit;
import com.techelevator.models.User;

public class UserService extends ApiServiceBase
{

	public UserService(String url)
	{
		super(url);
		this.BASE_URL = url + "users";
	}
	
	public User addMoney(BigDecimal amount)
	{
		String url = BASE_URL + "/deposit/" + user.getUser().getId();
		
		Deposit deposit = new Deposit(amount);

		HttpEntity<Deposit> entity = makeEntity(deposit);
		User user = restTemplate.postForObject(url, entity, User.class);
		
		return user;
	}

	
	private HttpEntity<Deposit> makeEntity(Deposit deposit)
	{
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		// add bearer token if user is logged in
		if(user != null)
		{
			headers.setBearerAuth(user.getToken());
		}
		
		HttpEntity<Deposit> entity = new HttpEntity<Deposit>(deposit, headers);
		
		return entity;
	}
}
