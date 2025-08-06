package com.workouttracker.api.dto;

import lombok.Data;

@Data
public class ResetPasswordRequestDto {
    private String email;
    private String otp;
    private String newPassword;
} 