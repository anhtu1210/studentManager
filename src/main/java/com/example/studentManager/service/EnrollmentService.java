package com.example.studentManager.service;

import com.example.studentManager.entity.Enrollment;
import com.example.studentManager.repository.EnrollmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;

    public EnrollmentService(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    public List<Enrollment> getAll() {
        return enrollmentRepository.findAll();
    }

    public Enrollment getById(Long id) {
        return enrollmentRepository.findById(id).orElse(null);
    }

    public void save(Enrollment enrollment) {
        enrollmentRepository.save(enrollment);
    }

    public void delete(Long id) {
        enrollmentRepository.deleteById(id);
    }

    public List<Enrollment> search(String keyword) {
        return enrollmentRepository.findByStudentFullNameContainingIgnoreCaseOrSubjectSubjectNameContainingIgnoreCaseOrClassroomClassNameContainingIgnoreCase(keyword, keyword, keyword);
    }
}
