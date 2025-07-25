package com.workouttracker.api.controllers;

import com.workouttracker.api.dto.UserDto;
import com.workouttracker.api.dto.UserRegistrationDto;
import com.workouttracker.api.services.UserService;
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
} 