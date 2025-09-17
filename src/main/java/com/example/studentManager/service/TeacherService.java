package com.example.studentManager.service;

import com.example.studentManager.entity.Teacher;
import java.util.List;

public interface TeacherService {
    List<Teacher> getAll();
    Teacher getById(Long id);
    Teacher save(Teacher teacher);
    void delete(Long id);
    List<Teacher> search(String keyword);
}
