package com.example.studentManager.controller;

import com.example.studentManager.entity.Grade;
import com.example.studentManager.entity.Enrollment;
import com.example.studentManager.service.GradeService;
import com.example.studentManager.service.EnrollmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/grades")
public class GradeController {

    private final GradeService gradeService;
    private final EnrollmentService enrollmentService;

    public GradeController(GradeService gradeService, EnrollmentService enrollmentService) {
        this.gradeService = gradeService;
        this.enrollmentService = enrollmentService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("grades", gradeService.getAll());
        return "grades/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("grade", new Grade());
        model.addAttribute("enrollments", enrollmentService.getAll()); // để chọn sinh viên-môn
        return "grades/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Grade grade,
                       @RequestParam(value = "enrollmentId", required = false) Long enrollmentId) {
        if (enrollmentId != null) {
            Enrollment enrollment = enrollmentService.getById(enrollmentId);
            grade.setEnrollment(enrollment);
        } else {
            grade.setEnrollment(null);
        }
        // Tính điểm tổng kết (nếu chưa có)
        if (grade.getMidtermScore() != null && grade.getFinalScore() != null) {
            grade.setTotalScore((grade.getMidtermScore() * 0.4) + (grade.getFinalScore() * 0.6));
        }
        gradeService.save(grade);
        return "redirect:/grades";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Grade grade = gradeService.getById(id);
        if (grade == null) {
            return "redirect:/grades?error=Grade%20not%20found";
        }
        model.addAttribute("grade", grade);
        model.addAttribute("enrollments", enrollmentService.getAll());
        return "grades/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        gradeService.delete(id);
        return "redirect:/grades";
    }
}
