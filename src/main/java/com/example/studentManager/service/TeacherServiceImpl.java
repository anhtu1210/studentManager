package com.example.studentManager.service;

import com.example.studentManager.entity.Teacher;
import com.example.studentManager.repository.TeacherRepository;
import com.example.studentManager.service.TeacherService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public List<Teacher> getAll() {
        return teacherRepository.findAll();
    }

    @Override
    public Teacher getById(Long id) {
        return teacherRepository.findById(id).orElse(null);
    }

    @Override
    public Teacher save(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    public void delete(Long id) {
        teacherRepository.deleteById(id);
    }

    @Override
    public List<Teacher> search(String keyword) {
        return teacherRepository.findByFullNameContainingIgnoreCaseOrTeacherCodeContainingIgnoreCase(keyword, keyword);
    }
}
