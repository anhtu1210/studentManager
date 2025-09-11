package com.example.studentManager.controller;

import com.example.studentManager.entity.Student;
import com.example.studentManager.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
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
        return "students/student-form";
    }

    // Lưu sinh viên
    @PostMapping
    public String saveStudent(@ModelAttribute Student student) {
        studentService.saveStudent(student);
        return "redirect:/students";
    }

    // Form sửa
    @GetMapping("/edit/{id}")
    public String editStudentForm(@PathVariable Long id, Model model) {
        model.addAttribute("student", studentService.getStudentById(id));
        return "students/student-form";
    }

    // Xóa sinh viên
    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return "redirect:/students";
    }

}
