package com.example.studentManager.service;

import com.example.studentManager.entity.Classroom;

import java.util.List;

public interface ClassroomService {
    List<Classroom> getAll();
    Classroom getById(Long id);
    Classroom save(Classroom classroom);
    void delete(Long id) throws IllegalStateException;
}
