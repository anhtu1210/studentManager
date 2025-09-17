package com.example.studentManager.controller;

import com.example.studentManager.service.DataInitializationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/data")
public class DataController {

    private final DataInitializationService dataInitializationService;

    public DataController(DataInitializationService dataInitializationService) {
        this.dataInitializationService = dataInitializationService;
    }

    @GetMapping("/init")
    public String initializeData() {
        dataInitializationService.initializeSampleData();
        return "redirect:/dashboard?message=Data initialized successfully";
    }
}
