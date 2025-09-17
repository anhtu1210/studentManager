package com.example.studentManager.controller;

import com.example.studentManager.entity.Student;
import com.example.studentManager.service.DashboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
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
    
    @GetMapping("/api/students/by-performance")
    public ResponseEntity<List<Student>> getStudentsByPerformance(@RequestParam String category) {
        List<Student> students = dashboardService.getStudentsByPerformance(category);
        return ResponseEntity.ok(students);
    }
}
