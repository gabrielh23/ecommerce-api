package com.gabriel.ecommerce.service;

import com.gabriel.ecommerce.dto.request.LoginRequest;
import com.gabriel.ecommerce.dto.response.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest request);
}