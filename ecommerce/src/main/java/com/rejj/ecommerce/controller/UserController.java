package com.rejj.ecommerce.controller;

import com.rejj.ecommerce.dto.UpdateUserRequest;
import com.rejj.ecommerce.model.User;
import com.rejj.ecommerce.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<User> getProfile(@RequestAttribute Long userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @PutMapping("/profile")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<User> updateProfile(
            @RequestAttribute Long userId,
            @RequestBody UpdateUserRequest request) {
        return ResponseEntity.ok(userService.updateUser(userId, request));
    }

    @GetMapping("/admin/users")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PutMapping("/admin/users/{id}/block")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> blockUser(@PathVariable Long id) {
        userService.blockUser(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/admin/users/{id}/unblock")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> unblockUser(@PathVariable Long id) {
        userService.unblockUser(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/admin/users/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
} 