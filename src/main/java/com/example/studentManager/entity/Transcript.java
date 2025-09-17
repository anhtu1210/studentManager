package com.example.studentManager.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "transcripts")
@Getter
@Setter
public class Transcript {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Liên kết tới student
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    // Liên kết tới subject
    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    // Điểm tổng kết (hoặc tính từ Grade)
    private Double finalScore;

    private String gradeLetter; // A, B, C, D, F

    public Double getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(Double finalScore) {
        this.finalScore = finalScore;
    }
}
