package com.example.studentManager.service;

import com.example.studentManager.entity.*;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DashboardService {
    
    private final StudentService studentService;
    private final TeacherService teacherService;
    private final ClassroomService classroomService;
    private final SubjectService subjectService;
    private final EnrollmentService enrollmentService;
    private final GradeService gradeService;
    private final TranscriptService transcriptService;
    
    public DashboardService(StudentService studentService, TeacherService teacherService,
                          ClassroomService classroomService, SubjectService subjectService,
                          EnrollmentService enrollmentService, GradeService gradeService,
                          TranscriptService transcriptService) {
        this.studentService = studentService;
        this.teacherService = teacherService;
        this.classroomService = classroomService;
        this.subjectService = subjectService;
        this.enrollmentService = enrollmentService;
        this.gradeService = gradeService;
        this.transcriptService = transcriptService;
    }
    
    public Map<String, Object> getDashboardStatistics() {
        Map<String, Object> stats = new HashMap<>();
        
        // Basic counts
        stats.put("totalStudents", studentService.getAllStudents().size());
        stats.put("totalTeachers", teacherService.getAll().size());
        stats.put("totalClassrooms", classroomService.getAll().size());
        stats.put("totalSubjects", subjectService.getAll().size());
        stats.put("totalEnrollments", enrollmentService.getAll().size());
        stats.put("totalGrades", gradeService.getAll().size());
        
        // Student academic performance statistics
        List<Student> allStudents = studentService.getAllStudents();
        if (!allStudents.isEmpty()) {
            // Calculate average grade across all students
            double totalAverage = allStudents.stream()
                .mapToDouble(student -> {
                    List<Transcript> transcripts = transcriptService.getByStudent(student);
                    return transcripts.stream()
                        .filter(t -> t.getFinalScore() != null)
                        .mapToDouble(Transcript::getFinalScore)
                        .average()
                        .orElse(0.0);
                })
                .filter(avg -> avg > 0)
                .average()
                .orElse(0.0);

            stats.put("averageGrade", Math.round(totalAverage * 100.0) / 100.0);
            
            // Count students by academic performance
            long excellentStudents = allStudents.stream()
                .filter(student -> {
                    List<Transcript> transcripts = transcriptService.getByStudent(student);
                    if (transcripts.isEmpty()) return false;
                    double avg = transcripts.stream()
                        .filter(t -> t.getFinalScore() != null)
                        .mapToDouble(Transcript::getFinalScore)
                        .average()
                        .orElse(0.0);
                    return avg >= 8.5;
                })
                .count();
            stats.put("excellentCount", excellentStudents);
            
            long goodStudents = allStudents.stream()
                .filter(student -> {
                    List<Transcript> transcripts = transcriptService.getByStudent(student);
                    if (transcripts.isEmpty()) return false;
                    double avg = transcripts.stream()
                        .filter(t -> t.getFinalScore() != null)
                        .mapToDouble(Transcript::getFinalScore)
                        .average()
                        .orElse(0.0);
                    return avg >= 7.0 && avg < 8.5;
                })
                .count();
            stats.put("goodCount", goodStudents);
            
            long averageStudents = allStudents.stream()
                .filter(student -> {
                    List<Transcript> transcripts = transcriptService.getByStudent(student);
                    if (transcripts.isEmpty()) return false;
                    double avg = transcripts.stream()
                        .filter(t -> t.getFinalScore() != null)
                        .mapToDouble(Transcript::getFinalScore)
                        .average()
                        .orElse(0.0);
                    return avg >= 5.5 && avg < 7.0;
                })
                .count();
            stats.put("averageCount", averageStudents);
            
            long poorStudents = allStudents.stream()
                .filter(student -> {
                    List<Transcript> transcripts = transcriptService.getByStudent(student);
                    if (transcripts.isEmpty()) return false;
                    double avg = transcripts.stream()
                        .filter(t -> t.getFinalScore() != null)
                        .mapToDouble(Transcript::getFinalScore)
                        .average()
                        .orElse(0.0);
                    return avg < 5.5;
                })
                .count();
            stats.put("poorCount", poorStudents);
            
            // Count students without grades
            long noGradesStudents = allStudents.stream()
                .filter(student -> {
                    List<Transcript> transcripts = transcriptService.getByStudent(student);
                    return transcripts.isEmpty() || transcripts.stream()
                        .allMatch(t -> t.getFinalScore() == null);
                })
                .count();
            stats.put("noGradesCount", noGradesStudents);
        } else {
            stats.put("averageGrade", 0.0);
            stats.put("excellentCount", 0);
            stats.put("goodCount", 0);
            stats.put("averageCount", 0);
            stats.put("poorCount", 0);
            stats.put("noGradesCount", 0);
        }
        
        return stats;
    }
    
    public List<Student> getStudentsByPerformanceCategory(String category) {
        return studentService.getAllStudents().stream()
            .filter(student -> {
                List<Transcript> transcripts = transcriptService.getByStudent(student);
                
                switch (category.toLowerCase()) {
                    case "excellent":
                        if (transcripts.isEmpty()) return false;
                        double excellentAvg = transcripts.stream()
                            .filter(t -> t.getFinalScore() != null)
                            .mapToDouble(Transcript::getFinalScore)
                            .average()
                            .orElse(0.0);
                        return excellentAvg >= 8.5;
                        
                    case "good":
                        if (transcripts.isEmpty()) return false;
                        double goodAvg = transcripts.stream()
                            .filter(t -> t.getFinalScore() != null)
                            .mapToDouble(Transcript::getFinalScore)
                            .average()
                            .orElse(0.0);
                        return goodAvg >= 7.0 && goodAvg < 8.5;
                        
                    case "average":
                        if (transcripts.isEmpty()) return false;
                        double averageAvg = transcripts.stream()
                            .filter(t -> t.getFinalScore() != null)
                            .mapToDouble(Transcript::getFinalScore)
                            .average()
                            .orElse(0.0);
                        return averageAvg >= 5.5 && averageAvg < 7.0;
                        
                    case "poor":
                        if (transcripts.isEmpty()) return false;
                        double poorAvg = transcripts.stream()
                            .filter(t -> t.getFinalScore() != null)
                            .mapToDouble(Transcript::getFinalScore)
                            .average()
                            .orElse(0.0);
                        return poorAvg < 5.5;
                        
                    case "nogrades":
                        return transcripts.isEmpty() || transcripts.stream()
                            .allMatch(t -> t.getFinalScore() == null);
                        
                    default:
                        return false;
                }
            })
            .toList();
    }
}