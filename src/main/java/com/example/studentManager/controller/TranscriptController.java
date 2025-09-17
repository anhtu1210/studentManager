package com.example.studentManager.controller;

import com.example.studentManager.entity.Student;
import com.example.studentManager.entity.Transcript;
import com.example.studentManager.service.StudentService;
import com.example.studentManager.service.TranscriptService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/transcripts")
public class TranscriptController {

    private final TranscriptService transcriptService;
    private final StudentService studentService;

    public TranscriptController(TranscriptService transcriptService, StudentService studentService) {
        this.transcriptService = transcriptService;
        this.studentService = studentService;
    }

    // Xem toàn bộ bảng điểm
    @GetMapping
    public String listAll(Model model) {
        model.addAttribute("transcripts", transcriptService.getAll());
        return "transcripts/list";
    }

    // Xem bảng điểm theo sinh viên
    @GetMapping("/student/{id}")
    public String listByStudent(@PathVariable Long id, Model model) {
        Student student = studentService.getStudentById(id);
        List<Transcript> transcripts = transcriptService.getByStudent(student);

        // Tính GPA trung bình
        double gpa = transcripts.stream()
                .mapToDouble(t -> t.getFinalScore() != null ? t.getFinalScore() : 0.0)
                .average().orElse(0.0);

        model.addAttribute("student", student);
        model.addAttribute("transcripts", transcripts);
        model.addAttribute("gpa", gpa);

        return "transcripts/student-transcript";
    }
}
