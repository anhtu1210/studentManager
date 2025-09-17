package com.example.studentManager.controller;

import com.example.studentManager.entity.Student;
import com.example.studentManager.service.StudentService;
import com.example.studentManager.entity.Classroom;
import com.example.studentManager.service.ClassroomService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;
    private final ClassroomService classroomService;

    public StudentController(StudentService studentService, ClassroomService classroomService) {
        this.studentService = studentService;
        this.classroomService = classroomService;
    }

    // Hiển thị danh sách
    @GetMapping
    public String listStudents(Model model, @RequestParam(value = "keyword", required = false) String keyword) {
        List<Student> list;
        if (keyword != null && !keyword.isEmpty()) {
            list = studentService.searchStudents(keyword);
        } else {
            list = studentService.getAllStudents();
        }
        System.out.println("Danh sách trả về: " + list); // debug
        model.addAttribute("students", list);
        return "students/students";
    }

    // Form thêm mới
    @GetMapping("/new")
    public String createStudentForm(Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("classrooms", classroomService.getAll());
        return "students/student-form";
    }

    @PostMapping
    public String saveStudent(@Valid @ModelAttribute("student") Student student,
                              BindingResult bindingResult,
                              Model model,
                              @RequestParam(value = "classroomId", required = false) Long classroomId) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("classrooms", classroomService.getAll());
            return "students/student-form";
        }
        if (classroomId != null) {
            Classroom cls = classroomService.getById(classroomId);
            student.setClassroom(cls);
        } else {
            student.setClassroom(null);
        }
        try {
            studentService.saveStudent(student);
        } catch (DataIntegrityViolationException ex) {
            bindingResult.rejectValue("email", "duplicate", "Email đã tồn tại");
            model.addAttribute("classrooms", classroomService.getAll());
            return "students/student-form";
        }
        return "redirect:/students";
    }

    @GetMapping("/edit/{id}")
    public String editStudentForm(@PathVariable Long id, Model model) {
        model.addAttribute("student", studentService.getStudentById(id));
        model.addAttribute("classrooms", classroomService.getAll());
        return "students/student-form";
    }

    // Xóa sinh viên
    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return "redirect:/students";
    }

}
