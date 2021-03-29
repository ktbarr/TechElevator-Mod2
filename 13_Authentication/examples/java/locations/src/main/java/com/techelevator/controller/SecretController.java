package com.techelevator.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/secret")
@Secured("ROLE_SUPER_ADMIN")
public class SecretController {

}
