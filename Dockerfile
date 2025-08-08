# Use a base image with Maven and JDK 17 to build the application
FROM maven:3.8.5-openjdk-17 AS build

# Set the working directory
WORKDIR /app

# Copy the project files into the container
COPY . .

# Build the application and create the executable JAR file
RUN mvn clean install -DskipTests


# Use a lightweight base image with JRE 17 for the final application
FROM eclipse-temurin:17

# Set the working directory
WORKDIR /app

# Copy the executable JAR from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port the application runs on
EXPOSE 8080

# Set the command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"] 