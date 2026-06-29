package com.example.ReadQuizBackend.dto;

import com.example.ReadQuizBackend.entity.User;

public class UserResponse {
    private Integer id;
    private String fullname;
    private String email;

    public UserResponse(User user) {
        this.id = user.getId();
        this.fullname = user.getFullname();
        this.email = user.getEmail();
    }

    public Integer getId() {
        return id;
    }

    public String getFullname() {
        return fullname;
    }

    public String getEmail() {
        return email;
    }
}
