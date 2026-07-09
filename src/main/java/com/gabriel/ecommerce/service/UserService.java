package com.gabriel.ecommerce.service;

import com.gabriel.ecommerce.dto.request.CreateUserRequest;
import com.gabriel.ecommerce.dto.response.UserResponse;

public interface UserService {
    UserResponse create(CreateUserRequest request);
}