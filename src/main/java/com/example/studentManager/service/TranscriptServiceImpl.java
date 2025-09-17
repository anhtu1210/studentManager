package com.example.studentManager.service;

import com.example.studentManager.entity.Transcript;
import com.example.studentManager.entity.Student;
import com.example.studentManager.entity.Grade;
import com.example.studentManager.repository.TranscriptRepository;
import com.example.studentManager.repository.GradeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;

@Service
public class TranscriptServiceImpl implements TranscriptService {

    private final TranscriptRepository transcriptRepository;
    private final GradeRepository gradeRepository;

    public TranscriptServiceImpl(TranscriptRepository transcriptRepository, GradeRepository gradeRepository) {
        this.transcriptRepository = transcriptRepository;
        this.gradeRepository = gradeRepository;
    }

    @Override
    public List<Transcript> getAll() {
        // Get existing transcripts from database first
        List<Transcript> existingTranscripts = transcriptRepository.findAll();
        
        // Generate transcripts from all grades
        List<Grade> allGrades = gradeRepository.findAll();
        List<Transcript> transcripts = new ArrayList<>();
        
        for (Grade grade : allGrades) {
            // Check if transcript already exists for this student-subject combination
            boolean exists = existingTranscripts.stream()
                .anyMatch(t -> t.getStudent().getId().equals(grade.getEnrollment().getStudent().getId()) &&
                              t.getSubject().getId().equals(grade.getEnrollment().getSubject().getId()));
            
            if (!exists) {
                Transcript transcript = new Transcript();
                transcript.setStudent(grade.getEnrollment().getStudent());
                transcript.setSubject(grade.getEnrollment().getSubject());
                transcript.setFinalScore(grade.getTotalScore());
                
                // Convert score to letter grade
                if (grade.getTotalScore() != null) {
                    if (grade.getTotalScore() >= 8.5) {
                        transcript.setGradeLetter("A");
                    } else if (grade.getTotalScore() >= 7.0) {
                        transcript.setGradeLetter("B");
                    } else if (grade.getTotalScore() >= 5.5) {
                        transcript.setGradeLetter("C");
                    } else if (grade.getTotalScore() >= 4.0) {
                        transcript.setGradeLetter("D");
                    } else {
                        transcript.setGradeLetter("F");
                    }
                }
                
                // Save to database
                transcript = transcriptRepository.save(transcript);
                transcripts.add(transcript);
            } else {
                // Use existing transcript
                Transcript existing = existingTranscripts.stream()
                    .filter(t -> t.getStudent().getId().equals(grade.getEnrollment().getStudent().getId()) &&
                                t.getSubject().getId().equals(grade.getEnrollment().getSubject().getId()))
                    .findFirst()
                    .orElse(null);
                if (existing != null) {
                    transcripts.add(existing);
                }
            }
        }
        
        return transcripts;
    }

    @Override
    public List<Transcript> getByStudent(Student student) {
        // Generate transcripts from grades for this student
        List<Grade> grades = gradeRepository.findByEnrollmentStudent(student);
        List<Transcript> transcripts = new ArrayList<>();
        
        for (Grade grade : grades) {
            Transcript transcript = new Transcript();
            transcript.setStudent(grade.getEnrollment().getStudent());
            transcript.setSubject(grade.getEnrollment().getSubject());
            transcript.setFinalScore(grade.getTotalScore());
            
            // Convert score to letter grade
            if (grade.getTotalScore() != null) {
                if (grade.getTotalScore() >= 8.5) {
                    transcript.setGradeLetter("A");
                } else if (grade.getTotalScore() >= 7.0) {
                    transcript.setGradeLetter("B");
                } else if (grade.getTotalScore() >= 5.5) {
                    transcript.setGradeLetter("C");
                } else if (grade.getTotalScore() >= 4.0) {
                    transcript.setGradeLetter("D");
                } else {
                    transcript.setGradeLetter("F");
                }
            }
            
            transcripts.add(transcript);
        }
        
        return transcripts;
    }

    @Override
    public Transcript save(Transcript transcript) {
        return transcriptRepository.save(transcript);
    }

    @Override
    public void delete(Long id) {
        transcriptRepository.deleteById(id);
    }
}
