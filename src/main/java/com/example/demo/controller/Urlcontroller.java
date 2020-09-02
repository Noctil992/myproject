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

    @GetMapping("urls/delete")
    public String deleteUrl(@ModelAttribute Url url, Model model, Integer id, Principal principal) {
        Url u = urlR.findOneByid(id);//URLのIDを持ってきて、対応するIDのオブジェクトを１件取得
        String a = u.getLoginUserId();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();//現在ログインしている人の情報取得

        model.addAttribute("urluser", a);
        model.addAttribute("logginuser", name);

        if(a.equals(name)) {
            urlR.deleteById(id);
        } else {
            model.addAttribute("error", "認証外ユーザーアクセスです。");
            return "errors";
        }
        return "redirect:/mypage";

    }
    
    @GetMapping("urls/edit")
    public String editPages(@ModelAttribute Url url, Model model, Integer id, Principal principal) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        model.addAttribute("username", name);
        
        Url u = urlR.findOneByid(id);
        String a = u.getLoginUserId();
        
        if(a.equals(name)) {
            model.addAttribute("url", u);
            return "urls/edit";
        } else {
            model.addAttribute("error", "認証外ユーザーアクセスです。");
            return "errors";
        }
        
        
        
    }
    
    @PostMapping("urls/update")
        public String update(@ModelAttribute Url url, Model model, Integer id, Principal principal) {
            Date currentTime = new Date(System.currentTimeMillis());
            url.setCreate_day(currentTime);
            urlR.save(url);
            return "redirect:/mypage";
        }
    
    @GetMapping("/good")
    public String good(@ModelAttribute Url url, Model model, Integer id, Principal principal) {
        Integer g = urlR.getOne(id).getGood_counter();
        g++;
        url.setGood_counter(g);
        urlR.save(url);
        return "redirect:/mypage";
    }
    
    
    
    
    }
    




