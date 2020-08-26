package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.repository.UsersRepository;

@Controller
public class TopPageController {
    @GetMapping("/")     // トップページ表示
    public String topPageindex() {
        return "toppage/topindex";
    }
    
    
    }

