package com.workouttracker.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class WorkoutTrackerApiApplication {

	public static void main(String[] args) {
		// Handle DATABASE_URL from Render (e.g., postgresql://user:pass@host:port/db)
		String databaseUrl = System.getenv("DATABASE_URL");
		if (databaseUrl != null) {
			try {
				java.net.URI dbUri = new java.net.URI(databaseUrl);
				String userInfo = dbUri.getUserInfo();
				if (userInfo != null) {
					String[] parts = userInfo.split(":", 2);
					if (parts.length == 2) {
						System.setProperty("spring.datasource.username", parts[0]);
						System.setProperty("spring.datasource.password", parts[1]);
					}
				}
				String host = dbUri.getHost();
				int port = dbUri.getPort();
				String path = dbUri.getPath();
				String jdbcUrl = String.format("jdbc:postgresql://%s:%d%s", host, port, path);
				System.setProperty("spring.datasource.url", jdbcUrl);
			} catch (Exception e) {
				// If parsing fails, ignore and rely on other config
			}
		}
		// Load .env for email creds if present
		io.github.cdimascio.dotenv.Dotenv dotenv = io.github.cdimascio.dotenv.Dotenv.configure().ignoreIfMissing().load();
		String emailUsername = dotenv.get("EMAIL_USERNAME");
		String emailPassword = dotenv.get("EMAIL_PASSWORD");
		if (emailUsername != null) {
			System.setProperty("spring.mail.username", emailUsername);
		}
		if (emailPassword != null) {
			System.setProperty("spring.mail.password", emailPassword);
		}

		SpringApplication.run(WorkoutTrackerApiApplication.class, args);
	}

}
