package com.gabriel.ecommerce.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateUserRequest(
    @NotBlank
    @Size(min = 3, max = 120)
    String name,

    @NotBlank
    @Email
    String email,

    @NotBlank
    @Size(min = 6, max = 100)
    String password
) {
}