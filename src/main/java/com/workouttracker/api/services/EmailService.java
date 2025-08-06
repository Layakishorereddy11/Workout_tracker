package com.workouttracker.api.services;

import com.workouttracker.api.models.Otp;
import com.workouttracker.api.repositories.OtpRepository;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class EmailService {

    private final JavaMailSender mailSender;
    private final OtpRepository otpRepository;

    public EmailService(JavaMailSender mailSender, OtpRepository otpRepository) {
        this.mailSender = mailSender;
        this.otpRepository = otpRepository;
    }

    public void sendOtpEmail(String to) {
        String otp = generateOtp();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Your OTP for Account Recovery");
        message.setText("Your OTP is: " + otp);
        mailSender.send(message);

        otpRepository.save(new Otp(to, otp, LocalDateTime.now().plusMinutes(10)));
    }

    private String generateOtp() {
        return String.format("%06d", new Random().nextInt(999999));
    }
} 