package com.example.demo.controller;

import java.security.Principal;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @PostMapping("/users/create")
    public String createUserPage(@Validated @ModelAttribute Users users,BindingResult result, Model model) {

        Optional<Users> ucheck = usersrepository.findById(users.getLogin_id());

        if(result.hasErrors()) {
            return "users/newUser";
        }

        if(ucheck.isPresent()) {
            return "redirect:/users/createerror";
        } else {

            usersrepository.save(users);
            return "creater";
        }
    }

    @GetMapping("/users/createerror")
    public String createErrorPage(RedirectAttributes re) {
        re.addFlashAttribute("errors", "true");
        return "redirect:/users/new";
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





}
