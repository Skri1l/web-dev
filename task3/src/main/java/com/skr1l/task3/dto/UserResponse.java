package com.skr1l.task3.dto;

public class UserResponse {
    private String token;

    public UserResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
