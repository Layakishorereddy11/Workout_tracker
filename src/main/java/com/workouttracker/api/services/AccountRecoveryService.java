package com.workouttracker.api.services;

import com.workouttracker.api.dto.ResetPasswordRequestDto;
import com.workouttracker.api.dto.VerifyOtpRequestDto;
import com.workouttracker.api.handler.InvalidOtpException;
import com.workouttracker.api.models.Otp;
import com.workouttracker.api.models.User;
import com.workouttracker.api.repositories.OtpRepository;
import com.workouttracker.api.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AccountRecoveryService {

    private final UserRepository userRepository;
    private final OtpRepository otpRepository;
    private final EmailService emailService;
    private final BCryptPasswordEncoder passwordEncoder;


    public AccountRecoveryService(UserRepository userRepository, OtpRepository otpRepository, EmailService emailService) {
        this.userRepository = userRepository;
        this.otpRepository = otpRepository;
        this.emailService = emailService;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public boolean forgotPassword(String email) {
        return userRepository.findByEmail(email)
                .map(user -> {
                    emailService.sendOtpEmail(email);
                    return true;
                }).orElse(false);
    }

    public void resetPassword(ResetPasswordRequestDto request) {
        if (verifyOtp(request.getEmail(), request.getOtp())) {
            userRepository.findByEmail(request.getEmail()).ifPresent(user -> {
                user.setPassword(passwordEncoder.encode(request.getNewPassword()));
                userRepository.save(user);
            });
        } else {
            throw new InvalidOtpException("Invalid or expired OTP.");
        }
    }

    public boolean forgotUsername(String email) {
        return userRepository.findByEmail(email)
                .map(user -> {
                    emailService.sendOtpEmail(email);
                    return true;
                }).orElse(false);
    }

    public String getUsernameByOtp(VerifyOtpRequestDto request) {
        if(verifyOtp(request.getEmail(), request.getOtp())) {
            return userRepository.findByEmail(request.getEmail())
                    .map(User::getUsername)
                    .orElseThrow(() -> new RuntimeException("User not found."));
        }
        throw new InvalidOtpException("Invalid or expired OTP.");
    }


    private boolean verifyOtp(String email, String otp) {
        return otpRepository.findByEmailAndOtp(email, otp)
                .map(storedOtp -> storedOtp.getExpirationTime().isAfter(LocalDateTime.now()))
                .orElse(false);
    }
} 