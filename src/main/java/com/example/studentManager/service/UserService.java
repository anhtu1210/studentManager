package com.example.studentManager.service;

import com.example.studentManager.entity.User;
import com.example.studentManager.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository repo;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public void register(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole("MANAGER");
        repo.save(user);
    }

    public User findByUsername(String username) {
        return repo.findByUsername(username);
    }
}
