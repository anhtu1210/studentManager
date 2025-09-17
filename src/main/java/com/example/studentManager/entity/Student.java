package com.example.studentManager.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "students")
@Getter
@Setter
@ToString
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @NotBlank(message = "Mã SV không được để trống")
    private String studentCode;

    @NotBlank(message = "Họ & Tên không được để trống")
    private String fullName;

    @Email(message = "Email không hợp lệ")
    @Column(unique = true)
    private String email;

    // Thay trường classroom String bằng relation
    @ManyToOne
    @JoinColumn(name = "classroom_id")
    private Classroom classroom;

    // giữ birthday là String (hoặc change thành LocalDate nếu muốn)
    private String birthday;

    // Explicit setter to ensure availability during compilation
    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }
}
