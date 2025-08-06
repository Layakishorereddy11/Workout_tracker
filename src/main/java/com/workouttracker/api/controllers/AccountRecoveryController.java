package com.workouttracker.api.controllers;

import com.workouttracker.api.dto.ForgotPasswordRequestDto;
import com.workouttracker.api.dto.ResetPasswordRequestDto;
import com.workouttracker.api.dto.VerifyOtpRequestDto;
import com.workouttracker.api.services.AccountRecoveryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account-recovery")
public class AccountRecoveryController {

    private final AccountRecoveryService accountRecoveryService;

    public AccountRecoveryController(AccountRecoveryService accountRecoveryService) {
        this.accountRecoveryService = accountRecoveryService;
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody ForgotPasswordRequestDto request) {
        boolean userExists = accountRecoveryService.forgotPassword(request.getEmail());
        if (userExists) {
            return ResponseEntity.ok("OTP sent to your email.");
        } else {
            return ResponseEntity.status(404).body("User not found with this email.");
        }
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequestDto request) {
        accountRecoveryService.resetPassword(request);
        return ResponseEntity.ok("Password reset successfully.");
    }

    @PostMapping("/forgot-username")
    public ResponseEntity<String> forgotUsername(@RequestBody ForgotPasswordRequestDto request) {
        boolean userExists = accountRecoveryService.forgotUsername(request.getEmail());
        if (userExists) {
            return ResponseEntity.ok("OTP sent to your email.");
        } else {
            return ResponseEntity.status(404).body("User not found with this email.");
        }
    }

    @PostMapping("/verify-otp-username")
    public ResponseEntity<String> getUsernameByOtp(@RequestBody VerifyOtpRequestDto request) {
        String username = accountRecoveryService.getUsernameByOtp(request);
        return ResponseEntity.ok(username);
    }
} 