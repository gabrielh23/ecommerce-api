package com.gabriel.ecommerce.controller;

import com.gabriel.ecommerce.dto.request.LoginRequest;
import com.gabriel.ecommerce.dto.response.LoginResponse;
import com.gabriel.ecommerce.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody @Valid LoginRequest request) {
        return authService.login(request);
    }
}