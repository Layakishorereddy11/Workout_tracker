package com.workouttracker.api.services;

import com.workouttracker.api.dto.UserDto;
import com.workouttracker.api.dto.UserRegistrationDto;
import com.workouttracker.api.mappers.UserMapper;
import com.workouttracker.api.models.User;
import com.workouttracker.api.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserDto registerUser(UserRegistrationDto registrationDto) {
        // Check if user already exists
        if (userRepository.findByUsername(registrationDto.getUsername()).isPresent()) {
            throw new RuntimeException("Username is already taken");
        }
        if (userRepository.findByEmail(registrationDto.getEmail()).isPresent()) {
            throw new RuntimeException("Email is already registered");
        }

        // In a real world, it should be hashed the password here before saving
        User newUser = userMapper.toUser(registrationDto);

        User savedUser = userRepository.save(newUser);
        return userMapper.toUserDto(savedUser);
    }
} 