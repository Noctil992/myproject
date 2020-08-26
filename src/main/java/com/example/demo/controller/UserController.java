package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.repository.UsersRepository;

@Controller
public class UserController {
    @Autowired
    UsersRepository usersrepository;
    
    @GetMapping("/users/new")
    public String newUsersPage() {
        return "users/newUser";
    }
    
    @GetMapping("/users/login")
    public String loginPage() {
        return "users/login";
    }
}
