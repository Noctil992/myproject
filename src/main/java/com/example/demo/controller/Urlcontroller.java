package com.example.demo.controller;

import java.security.Principal;
import java.sql.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.models.Url;
import com.example.demo.repository.UrlRepository;



@Controller
public class Urlcontroller {
    @Autowired
    UrlRepository urlR;

    @GetMapping("/urls/new")
    public String createUrl(@ModelAttribute Url url, Model model, Principal principal) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();//get logged in username
        model.addAttribute("username", name);
        return "urls/urlNew";
    }

    @PostMapping("/urls/create")
    public String createUserPage(@Validated @ModelAttribute Url url,BindingResult result, Model model, Principal principal) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();//get logged in username
        model.addAttribute("username", name);
        
        if(result.hasErrors()) {
            return "urls/urlNew";
        }
        Date currentTime = new Date(System.currentTimeMillis());
        url.setCreate_day(currentTime);
        urlR.save(url);
        return "redirect:/mypage";
    }
}

