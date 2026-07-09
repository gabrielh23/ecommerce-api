package com.gabriel.ecommerce.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateCategoryRequest(
    @NotBlank
    @Size(min = 3, max = 120)
    String name,

    @Size(max = 500)
    String description,

    Boolean active
) {
}