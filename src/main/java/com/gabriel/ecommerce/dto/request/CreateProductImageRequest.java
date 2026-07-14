package com.gabriel.ecommerce.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record CreateProductImageRequest(

        @NotBlank
        @Size(max = 1000)
        String url,

        @Size(max = 255)
        String altText,

        @NotNull
        @Min(0)
        Integer position,

        @NotNull
        Boolean mainImage,

        @NotNull
        UUID productId

) {
}