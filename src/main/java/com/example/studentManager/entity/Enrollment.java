package com.example.studentManager.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "enrollments")
@Getter
@Setter
@ToString
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Liên kết Student
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    // Liên kết Subject
    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    // Liên kết Classroom
    @ManyToOne
    @JoinColumn(name = "classroom_id", nullable = false)
    private Classroom classroom;

    // Điểm số (có thể để null nếu chưa nhập)
    @Column(name = "grade")
    private Double grade;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
