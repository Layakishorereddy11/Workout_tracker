package com.workouttracker.api.dto;

import lombok.Data;

@Data
public class VerifyOtpRequestDto {
    private String email;
    private String otp;
} 