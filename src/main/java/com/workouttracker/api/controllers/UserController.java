package com.workouttracker.api.controllers;

import com.workouttracker.api.dto.UserDto;
import com.workouttracker.api.dto.UserLoginDto;
import com.workouttracker.api.dto.LoginResponseDto;
import com.workouttracker.api.dto.UserRegistrationDto;
import com.workouttracker.api.services.UserService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserRegistrationDto registrationDto) {
        UserDto newUser = userService.registerUser(registrationDto);
        return ResponseEntity.ok(newUser);
    }

    // UserController.java
    @GetMapping("/usernames")
    public ResponseEntity<List<String>> getAllUsernames() {
        List<String> usernames = userService.getAllUsernames();
        return ResponseEntity.ok(usernames);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody UserLoginDto loginDto) {
        LoginResponseDto response = userService.login(loginDto);
        return ResponseEntity.ok(response);
    }

} 