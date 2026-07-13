package com.gabriel.ecommerce.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record InventoryResponse(

        UUID id,

        Integer quantity,

        Integer reservedQuantity,

        Integer availableQuantity,

        UUID productVariantId,

        String sku,

        LocalDateTime createdAt,

        LocalDateTime updatedAt

) {
}