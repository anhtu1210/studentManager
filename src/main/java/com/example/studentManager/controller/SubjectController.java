package com.example.studentManager.controller;

import com.example.studentManager.entity.Subject;
import com.example.studentManager.entity.Teacher;
import com.example.studentManager.service.SubjectService;
import com.example.studentManager.service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/subjects")
public class SubjectController {

    private final SubjectService subjectService;
    private final TeacherService teacherService;

    public SubjectController(SubjectService subjectService, TeacherService teacherService) {
        this.subjectService = subjectService;
        this.teacherService = teacherService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("subjects", subjectService.getAll());
        return "subjects/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("subject", new Subject());
        model.addAttribute("teachers", teacherService.getAll());
        model.addAttribute("isEdit", false);
        return "subjects/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Subject subject,
                       @RequestParam(value = "teacherId", required = false) Long teacherId,
                       Model model) {
        if (teacherId != null) {
            Teacher t = teacherService.getById(teacherId);
            subject.setTeacher(t);
        } else {
            subject.setTeacher(null);
        }
        subjectService.save(subject);
        return "redirect:/subjects";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Subject subject = subjectService.getById(id);
        if (subject == null) {
            return "redirect:/subjects?error=notfound";
        }
        model.addAttribute("subject", subject);
        model.addAttribute("teachers", teacherService.getAll());
        model.addAttribute("isEdit", true);
        return "subjects/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        subjectService.delete(id);
        return "redirect:/subjects";
    }
}
