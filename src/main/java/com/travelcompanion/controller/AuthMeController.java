package com.travelcompanion.controller;

import com.travelcompanion.dto.AuthResponse;
import com.travelcompanion.entity.User;
import com.travelcompanion.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthMeController {

    private final UserService userService;

    public AuthMeController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Returns the currently authenticated user's basic info.
     * Used by the frontend to rehydrate auth state after page refresh.
     */
    @GetMapping("/me")
    public ResponseEntity<AuthResponse> me() {
        User user = userService.getCurrentUser();
        return ResponseEntity.ok(new AuthResponse(
                null, // token is not re-issued
                user.getEmail(),
                user.getFullName(),
                user.getRoles().stream().map(Enum::name).toList()
        ));
    }
}
