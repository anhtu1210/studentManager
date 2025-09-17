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

    // Xem toàn bộ bảng điểm với filter
    @GetMapping
    public String listAll(@RequestParam(required = false) String keyword,
                         @RequestParam(required = false) String gradeFilter,
                         @RequestParam(required = false) String scoreRange,
                         Model model) {
        List<Transcript> transcripts = transcriptService.getAll();
        
        // Apply filters
        if (keyword != null && !keyword.trim().isEmpty()) {
            transcripts = transcripts.stream()
                    .filter(t -> t.getStudent().getFullName().toLowerCase().contains(keyword.toLowerCase()) ||
                               t.getStudent().getStudentCode().toLowerCase().contains(keyword.toLowerCase()) ||
                               t.getSubject().getSubjectName().toLowerCase().contains(keyword.toLowerCase()) ||
                               t.getSubject().getSubjectCode().toLowerCase().contains(keyword.toLowerCase()))
                    .collect(java.util.stream.Collectors.toList());
        }
        
        if (gradeFilter != null && !gradeFilter.isEmpty()) {
            transcripts = transcripts.stream()
                    .filter(t -> t.getGradeLetter().equals(gradeFilter))
                    .collect(java.util.stream.Collectors.toList());
        }
        
        if (scoreRange != null && !scoreRange.isEmpty()) {
            switch (scoreRange) {
                case "excellent":
                    transcripts = transcripts.stream()
                            .filter(t -> t.getFinalScore() >= 8.0)
                            .collect(java.util.stream.Collectors.toList());
                    break;
                case "good":
                    transcripts = transcripts.stream()
                            .filter(t -> t.getFinalScore() >= 6.5 && t.getFinalScore() < 8.0)
                            .collect(java.util.stream.Collectors.toList());
                    break;
                case "average":
                    transcripts = transcripts.stream()
                            .filter(t -> t.getFinalScore() >= 5.0 && t.getFinalScore() < 6.5)
                            .collect(java.util.stream.Collectors.toList());
                    break;
                case "poor":
                    transcripts = transcripts.stream()
                            .filter(t -> t.getFinalScore() < 5.0)
                            .collect(java.util.stream.Collectors.toList());
                    break;
            }
        }
        
        model.addAttribute("transcripts", transcripts);
        model.addAttribute("keyword", keyword);
        model.addAttribute("gradeFilter", gradeFilter);
        model.addAttribute("scoreRange", scoreRange);
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
