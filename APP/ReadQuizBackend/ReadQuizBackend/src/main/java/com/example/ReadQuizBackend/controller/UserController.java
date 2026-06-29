package com.example.ReadQuizBackend.controller;

import com.example.ReadQuizBackend.dto.ApiResponse;
import com.example.ReadQuizBackend.dto.LoginRequest;
import com.example.ReadQuizBackend.dto.RegisterRequest;
import com.example.ReadQuizBackend.dto.UserResponse;
import com.example.ReadQuizBackend.entity.User;
import com.example.ReadQuizBackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserResponse>> register(@RequestBody RegisterRequest request) {
        try {
            User user = userService.register(request);
            return ResponseEntity.ok(new ApiResponse<>(true, "Dang ky thanh cong", new UserResponse(user)));
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(false, exception.getMessage(), null));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<UserResponse>> login(@RequestBody LoginRequest request) {
        try {
            User user = userService.login(request);
            return ResponseEntity.ok(new ApiResponse<>(true, "Dang nhap thanh cong", new UserResponse(user)));
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(false, exception.getMessage(), null));
        }
    }
}
