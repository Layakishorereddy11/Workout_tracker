package com.workouttracker.api.controllers;

import com.workouttracker.api.dto.WorkoutDto;
import com.workouttracker.api.services.WorkoutService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/{userId}/workouts")
public class WorkoutController {

    private final WorkoutService workoutService;

    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @GetMapping
    public ResponseEntity<List<WorkoutDto>> getWorkoutsForUser(@PathVariable Long userId) {
        List<WorkoutDto> workouts = workoutService.getWorkoutsForUser(userId);
        return ResponseEntity.ok(workouts);
    }

    @GetMapping("/{workoutId}")
    public ResponseEntity<WorkoutDto> getWorkoutById(@PathVariable Long userId, @PathVariable Long workoutId) {
        WorkoutDto workout = workoutService.getWorkoutById(userId, workoutId);
        return ResponseEntity.ok(workout);
    }

    @PostMapping
    public ResponseEntity<WorkoutDto> createWorkout(@PathVariable Long userId, @RequestBody WorkoutDto workoutDto) {
        WorkoutDto createdWorkout = workoutService.createWorkout(userId, workoutDto);
        return ResponseEntity.ok(createdWorkout);
    }

    @PutMapping("/{workoutId}")
    public ResponseEntity<WorkoutDto> updateWorkout(@PathVariable Long userId, @PathVariable Long workoutId, @RequestBody WorkoutDto workoutDto) {
        WorkoutDto updatedWorkout = workoutService.updateWorkout(userId, workoutId, workoutDto);
        return ResponseEntity.ok(updatedWorkout);
    }

    @DeleteMapping("/{workoutId}")
    public ResponseEntity<Void> deleteWorkout(@PathVariable Long userId, @PathVariable Long workoutId) {
        workoutService.deleteWorkout(userId, workoutId);
        return ResponseEntity.noContent().build();
    }
} 