package com.gabriel.ecommerce.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateInventoryRequest(

    @NotNull
    UUID productVariantId,

    @NotNull
    @Min(0)
    Integer quantity

) {
}