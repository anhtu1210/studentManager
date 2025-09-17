package com.example.studentManager.repository;

import com.example.studentManager.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    List<Teacher> findByFullNameContainingIgnoreCaseOrTeacherCodeContainingIgnoreCase(String fullName, String teacherCode);
}
