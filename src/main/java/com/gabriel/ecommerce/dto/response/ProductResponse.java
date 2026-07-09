package com.gabriel.ecommerce.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record ProductResponse(
    UUID id,
    String name,
    String slug,
    String description,
    BigDecimal price,
    Boolean active,
    UUID categoryId,
    String categoryName,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {
}