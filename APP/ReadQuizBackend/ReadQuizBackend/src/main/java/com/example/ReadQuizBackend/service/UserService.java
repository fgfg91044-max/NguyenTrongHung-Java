package com.example.ReadQuizBackend.service;

import com.example.ReadQuizBackend.dto.LoginRequest;
import com.example.ReadQuizBackend.dto.RegisterRequest;
import com.example.ReadQuizBackend.entity.User;
import com.example.ReadQuizBackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User register(RegisterRequest request) {
        String fullname = clean(request.getFullname());
        String email = clean(request.getEmail()).toLowerCase();
        String password = clean(request.getPassword());

        if (fullname.isEmpty() || email.isEmpty() || password.isEmpty()) {
            throw new IllegalArgumentException("Vui long nhap day du thong tin");
        }

        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Email da duoc su dung");
        }

        User user = new User();
        user.setFullname(fullname);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));

        return userRepository.save(user);
    }

    public User login(LoginRequest request) {
        String email = clean(request.getEmail()).toLowerCase();
        String password = clean(request.getPassword());

        if (email.isEmpty() || password.isEmpty()) {
            throw new IllegalArgumentException("Vui long nhap email va mat khau");
        }

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Email hoac mat khau khong dung"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("Email hoac mat khau khong dung");
        }

        return user;
    }

    private String clean(String value) {
        return value == null ? "" : value.trim();
    }
}
