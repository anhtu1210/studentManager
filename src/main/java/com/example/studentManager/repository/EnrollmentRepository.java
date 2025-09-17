package com.example.studentManager.repository;

import com.example.studentManager.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    List<Enrollment> findByStudentFullNameContainingIgnoreCaseOrSubjectSubjectNameContainingIgnoreCaseOrClassroomClassNameContainingIgnoreCase(String studentName, String subjectName, String className);
}
