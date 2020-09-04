package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.demo.models.Url;
import com.example.demo.repository.UrlRepository;

@Controller
public class PublicPageController {
    @Autowired
    UrlRepository urlR;
    
    @GetMapping("public/main")
    public String publicPage(@ModelAttribute Url url, Model model) {
        Integer publicflag = 1;
        List<Url> urls = urlR.findTop5ByPublicflagOrderByIdDesc(publicflag); //最近登録された５件
        model.addAttribute("urls", urls);
        Url murl = urlR.findTop1ByPublicflagOrderByGoodcounterDesc(publicflag);
        model.addAttribute("murl", murl);
        Url nurl = urlR.findTop1ByPublicflagOrderByIdDesc(publicflag);
        model.addAttribute("nurl", nurl);
        return "public/main";
    }
}
