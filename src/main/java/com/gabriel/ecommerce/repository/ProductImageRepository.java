package com.gabriel.ecommerce.repository;

import com.gabriel.ecommerce.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductImageRepository
        extends JpaRepository<ProductImage, UUID> {

    List<ProductImage> findAllByProductIdOrderByPositionAsc(UUID productId);

    Optional<ProductImage> findByProductIdAndMainImageTrue(UUID productId);

    boolean existsByProductIdAndUrl(UUID productId, String url);
}