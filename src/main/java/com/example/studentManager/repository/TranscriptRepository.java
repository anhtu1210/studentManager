package com.example.studentManager.repository;

import com.example.studentManager.entity.Transcript;
import com.example.studentManager.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TranscriptRepository extends JpaRepository<Transcript, Long> {
    List<Transcript> findByStudent(Student student);
}
