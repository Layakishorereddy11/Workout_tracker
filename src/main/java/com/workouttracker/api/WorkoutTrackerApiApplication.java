package com.workouttracker.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class WorkoutTrackerApiApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.load();
		System.setProperty("spring.mail.username", dotenv.get("EMAIL_USERNAME"));
		System.setProperty("spring.mail.password", dotenv.get("EMAIL_PASSWORD"));

		SpringApplication.run(WorkoutTrackerApiApplication.class, args);
	}

}
