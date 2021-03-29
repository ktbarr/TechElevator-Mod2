package com.techelevator.vendingmachine.dao;

import java.util.List;

import com.techelevator.vendingmachine.models.security.User;

public interface UserDAO {

    List<User> findAll();
    
    User getUserById(Long userId);

    User findByUsername(String username);

    int findIdByUsername(String username);

    boolean create(String username, String password);
}
