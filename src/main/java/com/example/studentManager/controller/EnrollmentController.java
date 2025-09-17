package com.example.studentManager.controller;

import com.example.studentManager.entity.Enrollment;
import com.example.studentManager.entity.Student;
import com.example.studentManager.entity.Subject;
import com.example.studentManager.entity.Classroom;
import com.example.studentManager.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;
    private final StudentService studentService;
    private final SubjectService subjectService;
    private final ClassroomService classroomService;

    public EnrollmentController(
            EnrollmentService enrollmentService,
            StudentService studentService,
            SubjectService subjectService,
            ClassroomService classroomService) {
        this.enrollmentService = enrollmentService;
        this.studentService = studentService;
        this.subjectService = subjectService;
        this.classroomService = classroomService;
    }

    @GetMapping
    public String list(Model model, @RequestParam(value = "keyword", required = false) String keyword) {
        if (keyword != null && !keyword.isEmpty()) {
            model.addAttribute("enrollments", enrollmentService.search(keyword));
        } else {
            model.addAttribute("enrollments", enrollmentService.getAll());
        }
        return "enrollments/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("enrollment", new Enrollment());
        model.addAttribute("students", studentService.getAllStudents());
        model.addAttribute("subjects", subjectService.getAll());
        model.addAttribute("classrooms", classroomService.getAll());
        model.addAttribute("isEdit", false);
        return "enrollments/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Enrollment enrollment,
                       @RequestParam(value = "studentId", required = false) Long studentId,
                       @RequestParam(value = "subjectId", required = false) Long subjectId,
                       @RequestParam(value = "classroomId", required = false) Long classroomId,
                       Model model) {
        if (studentId != null) {
            Student s = studentService.getStudentById(studentId);
            enrollment.setStudent(s);
        } else {
            enrollment.setStudent(null);
        }
        if (subjectId != null) {
            Subject sub = subjectService.getById(subjectId);
            enrollment.setSubject(sub);
        } else {
            enrollment.setSubject(null);
        }
        if (classroomId != null) {
            Classroom c = classroomService.getById(classroomId);
            enrollment.setClassroom(c);
        } else {
            enrollment.setClassroom(null);
        }
        enrollmentService.save(enrollment);
        return "redirect:/enrollments";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Enrollment enrollment = enrollmentService.getById(id);
        if (enrollment == null) {
            return "redirect:/enrollments?error=notfound";
        }
        model.addAttribute("enrollment", enrollment);
        model.addAttribute("students", studentService.getAllStudents());
        model.addAttribute("subjects", subjectService.getAll());
        model.addAttribute("classrooms", classroomService.getAll());
        model.addAttribute("isEdit", true);
        return "enrollments/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        enrollmentService.delete(id);
        return "redirect:/enrollments";
    }
}
