package com.gabriel.ecommerce.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.UUID;

public record CreateProductVariantRequest(
        @NotBlank
        @Size(max = 100)
        String sku,

        @NotBlank
        @Size(max = 80)
        String color,

        @NotBlank
        @Size(max = 40)
        String size,

        @NotNull
        @DecimalMin(value = "0.01")
        BigDecimal price,

        @NotNull
        UUID productId
) {
}