package com.example.studentManager.service;

import com.example.studentManager.entity.Student;
import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();
    Student saveStudent(Student student);
    Student getStudentById(Long id);
    void deleteStudent(Long id);
    List<Student> searchStudents(String keyword);
}
