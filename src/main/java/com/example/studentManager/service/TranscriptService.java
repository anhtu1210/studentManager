package com.example.studentManager.service;

import com.example.studentManager.entity.Transcript;
import com.example.studentManager.entity.Student;

import java.util.List;

public interface TranscriptService {
    List<Transcript> getAll();
    List<Transcript> getByStudent(Student student);
    Transcript save(Transcript transcript);
    void delete(Long id);
}
