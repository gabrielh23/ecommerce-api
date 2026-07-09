package com.gabriel.ecommerce.controller;

import com.gabriel.ecommerce.dto.request.CreateUserRequest;
import com.gabriel.ecommerce.dto.response.UserResponse;
import com.gabriel.ecommerce.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse create(@RequestBody @Valid CreateUserRequest request) {
        return userService.create(request);
    }
}