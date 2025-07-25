package com.workouttracker.api.services;

import com.workouttracker.api.dto.WorkoutDto;
import com.workouttracker.api.mappers.WorkoutMapper;
import com.workouttracker.api.repositories.UserRepository;
import com.workouttracker.api.repositories.WorkoutRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import com.workouttracker.api.models.Workout;
import com.workouttracker.api.models.User;
import com.workouttracker.api.models.Exercise;

@Service
public class WorkoutService {

    private final WorkoutRepository workoutRepository;
    private final UserRepository userRepository;
    private final WorkoutMapper workoutMapper;

    public WorkoutService(WorkoutRepository workoutRepository, UserRepository userRepository, WorkoutMapper workoutMapper) {
        this.workoutRepository = workoutRepository;
        this.userRepository = userRepository;
        this.workoutMapper = workoutMapper;
    }

    public List<WorkoutDto> getWorkoutsForUser(Long userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        List<Workout> workouts = workoutRepository.findByUserId(userId);
        return workouts.stream()
                .map(workoutMapper::toWorkoutDto)
                .toList();
    }

    public WorkoutDto getWorkoutById(Long userId, Long workoutId) {
        Workout workout = workoutRepository.findById(workoutId)
                .orElseThrow(() -> new RuntimeException("Workout not found with id: " + workoutId));

        if (!workout.getUser().getId().equals(userId)) {
            throw new RuntimeException("Workout does not belong to the current user");
        }

        return workoutMapper.toWorkoutDto(workout);
    }

    public WorkoutDto createWorkout(Long userId, WorkoutDto workoutDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        Workout workout = workoutMapper.toWorkout(workoutDto);
        workout.setUser(user);

        // Ensure exercises are associated with the workout
        if (workout.getExercises() != null) {
            workout.getExercises().forEach(exercise -> exercise.setWorkout(workout));
        }

        Workout savedWorkout = workoutRepository.save(workout);
        return workoutMapper.toWorkoutDto(savedWorkout);
    }

    public WorkoutDto updateWorkout(Long userId, Long workoutId, WorkoutDto workoutDto) {
        Workout existingWorkout = workoutRepository.findById(workoutId)
                .orElseThrow(() -> new RuntimeException("Workout not found with id: " + workoutId));

        if (!existingWorkout.getUser().getId().equals(userId)) {
            throw new RuntimeException("Workout does not belong to the current user");
        }

        // Update basic properties
        existingWorkout.setWorkoutDate(workoutDto.getWorkoutDate());
        existingWorkout.setNotes(workoutDto.getNotes());

        // Clear existing exercises and add the new ones
        existingWorkout.getExercises().clear();
        if (workoutDto.getExercises() != null) {
            workoutDto.getExercises().forEach(exerciseDto -> {
                Exercise exercise = new Exercise(); // A mapper could also be used here
                exercise.setExerciseName(exerciseDto.getExerciseName());
                exercise.setSets(exerciseDto.getSets());
                exercise.setReps(exerciseDto.getReps());
                exercise.setWeightKg(exerciseDto.getWeightKg());
                exercise.setWorkout(existingWorkout);
                existingWorkout.getExercises().add(exercise);
            });
        }

        Workout updatedWorkout = workoutRepository.save(existingWorkout);
        return workoutMapper.toWorkoutDto(updatedWorkout);
    }

    public void deleteWorkout(Long userId, Long workoutId) {
        Workout workout = workoutRepository.findById(workoutId)
                .orElseThrow(() -> new RuntimeException("Workout not found with id: " + workoutId));

        if (!workout.getUser().getId().equals(userId)) {
            throw new RuntimeException("Workout does not belong to the current user");
        }

        workoutRepository.delete(workout);
    }
} 