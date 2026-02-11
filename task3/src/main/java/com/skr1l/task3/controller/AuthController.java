package com.skr1l.task3.controller;

import com.skr1l.task3.dto.UserRequest;
import com.skr1l.task3.dto.UserResponse;
import com.skr1l.task3.entity.User;
import com.skr1l.task3.security.JwtUtil;
import com.skr1l.task3.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthController(UserService userService,
                          AuthenticationManager authenticationManager,
                          JwtUtil jwtUtil) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public void register(@RequestBody UserRequest userRequest){
        userService.register(
                userRequest.getUsername(),
                userRequest.getPassword()
        );
    }

    @PostMapping("/login")
    public UserResponse login(@RequestBody UserRequest userRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userRequest.getUsername(),
                        userRequest.getPassword()
                )
        );

        String token = jwtUtil.generateToken(userRequest.getUsername());
        return new UserResponse(token);
    }

    @RestController
    public class TestController {

        @GetMapping("/hello")
        public String hello() {
            return "Hello";
        }
    }

}
