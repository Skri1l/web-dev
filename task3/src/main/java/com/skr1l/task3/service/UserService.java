package com.skr1l.task3.service;

import com.skr1l.task3.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final List<User> users = new ArrayList<>();
    private final PasswordEncoder passwordEncoder;

    public UserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public void register(String username, String password) {
        if (users.stream().anyMatch(u -> u.getUsername().equals(username))){
            throw new RuntimeException("User Exists");
        }
        users.add(new User(username, passwordEncoder.encode(password),"USER"));
    }

    public User findByUsername(String username) {
        return users.stream().filter(u -> u.getUsername().equals(username)).findFirst().orElse(null);
    }


}
