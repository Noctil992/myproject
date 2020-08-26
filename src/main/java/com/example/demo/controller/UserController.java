package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.models.Users;
import com.example.demo.repository.UsersRepository;

@Controller
public class UserController {
    @Autowired
    UsersRepository usersrepository;
    
    @GetMapping("/users/new")
    public String newUsersPage(@ModelAttribute Users users) {
        return "users/newUser";
    }
    
    @GetMapping("/users/login")
    public String loginPage() {
        return "users/login";
    }
    
    @PostMapping("/users/create")
    public String createUserPage(@Validated @ModelAttribute Users users, BindingResult result) {
        if (result.hasErrors()) {
            
            return "users/newUser";
        }
        usersrepository.save(users);
        return "toppage/topindex";
    }
}
