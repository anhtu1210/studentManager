package com.example.studentManager.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {

    @GetMapping("/profile")
    public String profile(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        
        model.addAttribute("username", username);
        model.addAttribute("isAuthenticated", authentication.isAuthenticated());
        model.addAttribute("authorities", authentication.getAuthorities());
        
        return "profile";
    }
}
