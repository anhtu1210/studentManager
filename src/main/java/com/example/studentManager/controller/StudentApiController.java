package com.example.studentManager.controller;

import com.example.studentManager.entity.Student;
import com.example.studentManager.service.DashboardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentApiController {

    private final DashboardService dashboardService;

    public StudentApiController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/by-performance")
    public List<Student> getStudentsByPerformance(@RequestParam String category) {
        return dashboardService.getStudentsByPerformanceCategory(category);
    }
}
