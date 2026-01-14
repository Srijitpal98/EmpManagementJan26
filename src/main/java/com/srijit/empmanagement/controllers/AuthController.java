package com.srijit.empmanagement.controllers;

import com.srijit.empmanagement.dtos.LoginRequest;
import com.srijit.empmanagement.dtos.RegisterRequest;
import com.srijit.empmanagement.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {

        authService.register(request);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {

        String token = authService.login(request);
        return ResponseEntity.ok(token);
    }
}
