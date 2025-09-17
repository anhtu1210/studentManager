package com.example.studentManager.controller;

import com.example.studentManager.entity.Teacher;
import com.example.studentManager.service.TeacherService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    public String list(Model model, @RequestParam(value = "keyword", required = false) String keyword) {
        if (keyword != null && !keyword.isEmpty()) {
            model.addAttribute("teachers", teacherService.search(keyword));
        } else {
            model.addAttribute("teachers", teacherService.getAll());
        }
        return "teachers/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("teacher", new Teacher());
        return "teachers/form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("teacher") Teacher teacher, BindingResult result) {
        if (result.hasErrors()) {
            return "teachers/form";
        }
        teacherService.save(teacher);
        return "redirect:/teachers";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Teacher teacher = teacherService.getById(id);
        if (teacher == null) {
            return "redirect:/teachers?error=NotFound";
        }
        model.addAttribute("teacher", teacher);
        return "teachers/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        teacherService.delete(id);
        return "redirect:/teachers";
    }
}
