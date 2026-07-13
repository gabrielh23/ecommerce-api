package com.gabriel.ecommerce.repository;

import com.gabriel.ecommerce.entity.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductVariantRepository
        extends JpaRepository<ProductVariant, UUID> {

    boolean existsBySku(String sku);

    Optional<ProductVariant> findBySku(String sku);

    List<ProductVariant> findAllByProductId(UUID productId);
}