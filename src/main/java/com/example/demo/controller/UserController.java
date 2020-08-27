package com.example.demo.controller;

import java.security.Principal;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.models.Users;
import com.example.demo.repository.CreateUserDao;
import com.example.demo.repository.UsersRepository;

@Controller
public class UserController {
    @Autowired
    UsersRepository usersrepository;
    CreateUserDao createDao;
    JdbcTemplate jdbc;
    EntityManager entityManager;

    @GetMapping("/users/new")
    public String newUsersPage(@ModelAttribute Users users,  Model model) {
        
        
        return "users/newUser";
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        return "users/login";
    }

    @GetMapping("/login1")
    public String goLogin(Model model, Principal principal) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object name = auth.getName();//get logged in username

        model.addAttribute("username", name);
        return "login1";
    }



    @PostMapping("/users/create")
    public String createUserPage(@ModelAttribute Users users) {
        Long before = usersrepository.count();
        usersrepository.save(users);
        Long after = usersrepository.count();
        if(before == after) {
            return "redirect:/";
        } else {
            return "creater";
        }
    }

}
