package com.example.studentManager.repository;

import com.example.studentManager.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
    // có thể thêm hàm tìm kiếm sau này
}
