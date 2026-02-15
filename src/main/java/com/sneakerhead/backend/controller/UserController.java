package com.sneakerhead.backend.controller;

import com.sneakerhead.backend.dto.response.UserResponse;
import com.sneakerhead.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * Get current authenticated user profile
     * 
     * @return Current user details
     */
    @GetMapping("/me")
    public ResponseEntity<UserResponse> getCurrentUser() {
        UserResponse user = userService.getCurrentUser();
        return ResponseEntity.ok(user);
    }

    /**
     * Get user by ID
     * 
     * @param id User ID
     * @return User details
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        UserResponse user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    /**
     * Get all users
     * 
     * @return List of all users
     */
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserResponse> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    /**
     * Update current user profile
     * 
     * @param userResponse Updated user details
     * @return Updated user profile
     */
    @PutMapping("/me")
    public ResponseEntity<UserResponse> updateProfile(@RequestBody UserResponse userResponse) {
        UserResponse updatedUser = userService.updateProfile(userResponse);
        return ResponseEntity.ok(updatedUser);
    }
}
