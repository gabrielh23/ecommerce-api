package com.gabriel.ecommerce.service.impl;

import com.gabriel.ecommerce.dto.request.CreateUserRequest;
import com.gabriel.ecommerce.dto.response.UserResponse;
import com.gabriel.ecommerce.entity.User;
import com.gabriel.ecommerce.enums.UserRole;
import com.gabriel.ecommerce.exception.BusinessException;
import com.gabriel.ecommerce.repository.UserRepository;
import com.gabriel.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponse create(CreateUserRequest request) {
        if (userRepository.findByEmail(request.email()).isPresent()) {
            throw new BusinessException("E-mail já cadastrado.");
        }

        User user = User.builder()
            .name(request.name())
            .email(request.email())
            .password(passwordEncoder.encode(request.password()))
            .role(UserRole.CUSTOMER)
            .build();

        User savedUser = userRepository.save(user);

        return new UserResponse(
            savedUser.getId(),
            savedUser.getName(),
            savedUser.getEmail(),
            savedUser.getCreatedAt()
        );
    }
}