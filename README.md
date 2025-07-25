# Workout Tracker API

This is the backend API for a personalized workout tracking application, built with Java and Spring Boot.



### Key Accomplishments:

*   **Project Initialization**:
    *   Initialized a robust Spring Boot 3 project using Maven.
    *   Configured essential dependencies including Spring Web, Spring Data JPA, PostgreSQL Driver, and Lombok.

*   **Database Setup**:
    *   Designed a relational database schema with `users`, `workouts`, and `exercises` tables.
    *   Included a `docker-compose.yml` file to easily spin up a PostgreSQL database for local development.

*   **Core API Functionality**:
    *   **Entities & Repositories**: Created JPA entities (`User`, `Workout`, `Exercise`) to model our database tables and Spring Data repositories for seamless database interaction.
    *   **DTOs & Mappers**: Implemented Data Transfer Objects (DTOs) to create a secure and flexible API layer, decoupling it from the database entities. Integrated MapStruct to handle the conversion between entities and DTOs automatically.
    *   **Controllers & Services**: Built a clean, layered architecture with controllers to handle API requests and services to contain the business logic.
    *   **Endpoints Implemented**:
        *   `POST /api/users/register`: For new user registration.
        *   `GET /api/users/{userId}/workouts`: To retrieve all workouts for a user.
        *   `POST /api/users/{userId}/workouts`: To create a new workout with exercises.
        *   `GET /api/users/{userId}/workouts/{workoutId}`: To retrieve a single workout.
        *   `PUT /api/users/{userId}/workouts/{workoutId}`: To update an existing workout.
        *   `DELETE /api/users/{userId}/workouts/{workoutId}`: To delete a workout.

*   **Endpoint Verification**:
    *   Successfully tested all implemented endpoints using `curl` to ensure they are working as expected.


## How to Run the Application

1.  **Start the database**:
    ```sh
    docker-compose up -d
    ```

2.  **Run the Spring Boot application**:
    ```sh
    ./mvnw spring-boot:run
    ```

The API will be available at `http://localhost:8080`.
