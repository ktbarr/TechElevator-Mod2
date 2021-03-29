package com.techelevator.controller;

import org.springframework.context.annotation.Role;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PublicController {

    @GetMapping("/public")
    public String home() {
        return "Hello, Public!";
    }

    @GetMapping("/secure")
    public String secure() {
        return "you must be authenticated";
    }

    @Secured("ROLE_USER")
    @GetMapping("/user")
    public String user() {
        return "Hello, User!";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/admin")
    public String admin() {
        return "Hello, Admin!";
    }
}