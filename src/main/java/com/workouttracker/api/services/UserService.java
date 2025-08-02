package com.workouttracker.api.services;

import com.workouttracker.api.dto.UserDto;
import com.workouttracker.api.dto.UserRegistrationDto;
import com.workouttracker.api.mappers.UserMapper;
import com.workouttracker.api.models.User;
import com.workouttracker.api.models.UserPrincipal;
import com.workouttracker.api.repositories.UserRepository;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService{

    private final UserRepository userRepository;
    private final UserMapper userMapper;

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
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            System.out.println("User Not Found");
            throw new UsernameNotFoundException("user not found");
        }
        
        return new UserPrincipal(user);
    }
} 