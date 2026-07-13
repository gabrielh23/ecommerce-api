package com.gabriel.ecommerce.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record ProductVariantResponse(
        UUID id,
        String sku,
        String color,
        String size,
        BigDecimal price,
        Boolean active,
        UUID productId,
        String productName,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}