package com.gabriel.ecommerce.service.impl;

import com.gabriel.ecommerce.dto.request.LoginRequest;
import com.gabriel.ecommerce.dto.response.LoginResponse;
import com.gabriel.ecommerce.entity.User;
import com.gabriel.ecommerce.exception.UnauthorizedException;
import com.gabriel.ecommerce.repository.UserRepository;
import com.gabriel.ecommerce.security.JwtService;
import com.gabriel.ecommerce.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.email())
            .orElseThrow(() -> new UnauthorizedException("E-mail ou senha inválidos."));

        boolean passwordMatches = passwordEncoder.matches(request.password(), user.getPassword());

        if (!passwordMatches) {
            throw new RuntimeException("E-mail ou senha inválidos.");
        }

        String token = jwtService.generateToken(user);

        return new LoginResponse(token);
    }
}