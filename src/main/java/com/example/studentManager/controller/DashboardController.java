package com.example.studentManager.controller;

import com.example.studentManager.service.DashboardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        Map<String, Object> stats = dashboardService.getDashboardStatistics();
        model.addAllAttributes(stats);
        model.addAttribute("pageTitle", "Dashboard - Hệ thống quản lý sinh viên");
        return "dashboard";
    }
}
