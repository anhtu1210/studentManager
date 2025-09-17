package com.example.studentManager.controller;

import com.example.studentManager.service.DashboardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class DebugController {

    private final DashboardService dashboardService;

    public DebugController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/debug/dashboard")
    public Map<String, Object> getDashboardData() {
        return dashboardService.getDashboardStatistics();
    }
}
