package com.example.demo.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.demo.models.Url;
import com.example.demo.repository.UrlRepository;

@Controller
public class MyPageController {
        @Autowired
        UrlRepository urlrepository;
    
        @GetMapping("/mypage")
        public String goLogin(@ModelAttribute Url url, Model model, Principal principal) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            Object name = auth.getName();//get logged in username
            List<Url> urllist = urlrepository.findAllByloginUserId(auth.getName());
            model.addAttribute("urllist", urllist);
            model.addAttribute("username", name);
            return "mypage/mypage";
        }
}
