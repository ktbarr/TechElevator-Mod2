package com.techelevator.vendingmachine.controllers;

import java.math.BigDecimal;
import java.security.Principal;

import com.techelevator.vendingmachine.dao.UserDAO;
import com.techelevator.vendingmachine.models.Deposit;
import com.techelevator.vendingmachine.models.security.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("isAuthenticated()")
@RequestMapping(path="/users")
public class UserController {

    @Autowired
    UserDAO dao;

    @PostMapping("/deposit/{id}")
    public User deposit(@RequestBody Deposit deposit, Principal principal)
    {
        String username = principal.getName();
        User user = dao.findByUsername(username);
        dao.deposit(user.getId(), deposit.getAmount());
        return dao.findByUsername(username);
    }
    
}
