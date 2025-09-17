package com.example.studentManager.controller;

import com.example.studentManager.entity.Classroom;
import com.example.studentManager.entity.Teacher;
import com.example.studentManager.service.ClassroomService;
import com.example.studentManager.service.TeacherService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/classrooms")
public class ClassroomController {

    private final ClassroomService classroomService;
    private final TeacherService teacherService;

    public ClassroomController(ClassroomService classroomService, TeacherService teacherService) {
        this.classroomService = classroomService;
        this.teacherService = teacherService;
    }

    @ModelAttribute
    public void ensureDefaults(Model model) {
        if (!model.containsAttribute("classroom")) {
            model.addAttribute("classroom", new Classroom());
        }
        if (!model.containsAttribute("isEdit")) {
            model.addAttribute("isEdit", false);
        }
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("classrooms", classroomService.getAll());
        return "classrooms/list";
    }

    @GetMapping("/new")
    public String createClassroomForm(Model model) {
        model.addAttribute("classroom", new Classroom());
        model.addAttribute("teachers", teacherService.getAll());
        model.addAttribute("isEdit", false);
        return "classrooms/form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("classroom") Classroom classroom,
                       BindingResult bindingResult,
                       Model model,
                       @RequestParam(value = "teacherId", required = false) Long teacherId) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("teachers", teacherService.getAll());
            model.addAttribute("isEdit", classroom.getId() != null);
            return "classrooms/form";
        }
        if (teacherId != null) {
            Teacher teacher = teacherService.getById(teacherId);
            classroom.setTeacher(teacher);
        } else {
            classroom.setTeacher(null);
        }
        classroomService.save(classroom);
        return "redirect:/classrooms";
    }

    @GetMapping("/edit/{id}")
    public String editClassroomForm(@PathVariable Long id, Model model) {
        Classroom classroom = classroomService.getById(id);
        if (classroom == null) {
            return "redirect:/classrooms?error=Classroom%20not%20found";
        }
        model.addAttribute("classroom", classroom);
        model.addAttribute("teachers", teacherService.getAll());
        model.addAttribute("isEdit", true);
        return "classrooms/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, Model model) {
        try {
            classroomService.delete(id);
        } catch (IllegalStateException ex) {
            model.addAttribute("error", ex.getMessage());
            model.addAttribute("classrooms", classroomService.getAll());
            return "classrooms/list";
        }
        return "redirect:/classrooms";
    }
}
