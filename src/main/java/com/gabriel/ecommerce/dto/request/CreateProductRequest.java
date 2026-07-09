package com.gabriel.ecommerce.dto.request;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.UUID;

public record CreateProductRequest(
    @NotBlank
    @Size(min = 3, max = 160)
    String name,

    @Size(max = 1000)
    String description,

    @NotNull
    @DecimalMin(value = "0.01")
    BigDecimal price,

    @NotNull
    UUID categoryId
) {
}