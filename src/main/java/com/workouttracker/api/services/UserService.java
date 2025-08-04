package com.workouttracker.api.services;

import com.workouttracker.api.dto.UserDto;
import com.workouttracker.api.dto.UserLoginDto;
import com.workouttracker.api.dto.LoginResponseDto;
import com.workouttracker.api.dto.UserRegistrationDto;
import com.workouttracker.api.mappers.UserMapper;
import com.workouttracker.api.models.User;
import com.workouttracker.api.repositories.UserRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService{

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    @Autowired
    private JWTService jwtService;
    
    @Autowired
    AuthenticationManager authManager;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    public UserDto registerUser(UserRegistrationDto registrationDto) {
        // Check if user already exists
        if (userRepository.findByUsername(registrationDto.getUsername()) != null) {
            throw new RuntimeException("Username is already taken");
        }
        if (userRepository.findByEmail(registrationDto.getEmail()).isPresent()) {
            throw new RuntimeException("Email is already registered");
        }

        // In a real world, it should be hashed the password here before saving
        User newUser = userMapper.toUser(registrationDto);
        newUser.setPassword(encoder.encode(newUser.getPassword()));
        User savedUser = userRepository.save(newUser);
        return userMapper.toUserDto(savedUser);
    }

    public List<String> getAllUsernames() {
        
        return userRepository.findAllUsernames();
    }
    

    public LoginResponseDto login(UserLoginDto loginDto) {
        Authentication authentication = authManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword())
        );
            User user = userRepository.findByUsername(loginDto.getUsername());
            String token = jwtService.generateToken(loginDto.getUsername());
            
            return new LoginResponseDto(userMapper.toUserDto(user), token);
    }
}