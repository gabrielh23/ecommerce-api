package com.gabriel.ecommerce.repository;

import com.gabriel.ecommerce.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface InventoryRepository
        extends JpaRepository<Inventory, UUID> {

    Optional<Inventory> findByProductVariantId(UUID productVariantId);

}