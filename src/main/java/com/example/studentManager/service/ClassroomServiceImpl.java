package com.example.studentManager.service;

import com.example.studentManager.entity.Classroom;
import com.example.studentManager.repository.ClassroomRepository;
import com.example.studentManager.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClassroomServiceImpl implements ClassroomService {

    private final ClassroomRepository classroomRepository;
    private final StudentRepository studentRepository;

    public ClassroomServiceImpl(ClassroomRepository classroomRepository,
                                StudentRepository studentRepository) {
        this.classroomRepository = classroomRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Classroom> getAll() {
        return classroomRepository.findAll();
    }

    @Override
    public Classroom getById(Long id) {
        return classroomRepository.findById(id).orElse(null);
    }

    @Override
    public Classroom save(Classroom classroom) {
        return classroomRepository.save(classroom);
    }

    @Override
    @Transactional
    public void delete(Long id) throws IllegalStateException {
        long cnt = studentRepository.countByClassroom_Id(id);
        if (cnt > 0) {
            throw new IllegalStateException("Không thể xóa lớp còn sinh viên (" + cnt + " sinh viên).");
        }
        classroomRepository.deleteById(id);
    }
}
