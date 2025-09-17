package com.example.studentManager.repository;

import com.example.studentManager.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByStudentCodeContainingOrFullNameContainingOrClassroom_ClassCodeContaining(
            String studentCode, String fullName, String classroomCode);

    long countByClassroom_Id(Long classroomId);
}
