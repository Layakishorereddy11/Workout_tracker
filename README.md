# Workout Tracker API

This is the backend API for a personalized workout tracking application, built with Java and Spring Boot.

### Technologies Used

*   **Java 17**
*   **Spring Boot 3**
*   **Spring Web**
*   **Spring Data JPA**
*   **Spring Security**
*   **PostgreSQL**
*   **Maven**
*   **Lombok**
*   **MapStruct**
*   **JWT**

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
    *   **Security**: Implemented JWT-based authentication and authorization to secure the API endpoints.

*   **API Endpoints**:
    *   `POST /api/users/register`: For new user registration.
    *   `POST /api/users/login`: For user login and to receive a JWT token.
    *   `GET /api/users/usernames`: To retrieve all usernames.
    *   `GET /api/users/{userId}/workouts`: To retrieve all workouts for a user.
    *   `POST /api/users/{userId}/workouts`: To create a new workout with exercises.
    *   `GET /api/users/{userId}/workouts/{workoutId}`: To retrieve a single workout.
    *   `PUT /api/users/{userId}/workouts/{workoutId}`: To update an existing workout.
    *   `DELETE /api/users/{userId}/workouts/{workoutId}`: To delete a workout.

*   **Endpoint Verification**:
    *   Successfully tested all implemented endpoints using `curl` and Bruno to ensure they are working as expected.

### Security

The application uses Spring Security to protect the API endpoints. Authentication is handled using JSON Web Tokens (JWT).

**Configuration (`SecurityConfig.java`)**

*   **CSRF Protection**: Disabled for stateless API.
*   **Authorization**:
    *   Public access is granted to `/api/users/register` and `/api/users/login`.
    *   All other requests require authentication.
*   **Session Management**: Configured to be stateless, as we are using JWTs for authentication.
*   **Authentication Provider**: A `DaoAuthenticationProvider` is used to authenticate users against the database. It uses `MyUserDetailsService` to load user details and `BCryptPasswordEncoder` to verify passwords.
*   **JWT Filter**: The `JwtFilter` is added to the security filter chain before the `UsernamePasswordAuthenticationFilter`. This filter is responsible for validating the JWT from the `Authorization` header and setting the user's authentication details in the `SecurityContext`.

**JWT Handling**

*   **`JwtFilter.java`**: This filter intercepts incoming requests to check for a JWT in the `Authorization` header. If a valid token is found, it extracts the username, validates the token, and sets the user's authentication details in the `SecurityContextHolder`.
*   **`JWTService.java`**: This service is responsible for generating, validating, and extracting information from JWTs.
*   **`MyUserDetailsService.java`**: This service implements the `UserDetailsService` interface to load user-specific data from the database. It is used by the `AuthenticationProvider` to authenticate users.

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
