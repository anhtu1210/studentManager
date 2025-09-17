package com.example.studentManager.service;

import com.example.studentManager.entity.Grade;
import com.example.studentManager.repository.GradeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GradeService {

    private final GradeRepository gradeRepository;

    public GradeService(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    public List<Grade> getAll() {
        return gradeRepository.findAll();
    }

    public Grade getById(Long id) {
        return gradeRepository.findById(id).orElse(null);
    }

    public Grade save(Grade grade) {
        return gradeRepository.save(grade);
    }

    public void delete(Long id) {
        gradeRepository.deleteById(id);
    }
}
