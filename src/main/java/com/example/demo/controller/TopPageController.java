package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class TopPageController {
    @GetMapping("/")     // トップページ表示
    public String topPageindex() {
        return "toppage/topindex";
    }
    
    
    }

