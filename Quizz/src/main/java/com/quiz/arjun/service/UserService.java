package com.quiz.arjun.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.quiz.arjun.entites.User;
import com.quiz.arjun.repository.UserRepository;

import jakarta.annotation.PostConstruct;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @PostConstruct
    public void createDefaultAdmin() {
        if (!userRepository.existsByUsername("admin")) {
            User admin = new User("admin", "admin123", User.Role.ADMIN);
            saveUser(admin);
        }
    }
}
