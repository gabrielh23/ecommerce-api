package com.gabriel.ecommerce.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record ProductImageResponse(

        UUID id,

        String url,

        String altText,

        Integer position,

        Boolean mainImage,

        UUID productId,

        String productName,

        LocalDateTime createdAt,

        LocalDateTime updatedAt

) {
}