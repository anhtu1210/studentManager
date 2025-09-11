package com.example.studentManager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("message", "Chào mừng bạn đến Dashboard!");
        return "dashboard"; // trỏ tới file dashboard.html
    }
}
