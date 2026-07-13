package com.gabriel.ecommerce.service.impl;

import com.gabriel.ecommerce.dto.request.CreateInventoryRequest;
import com.gabriel.ecommerce.dto.request.UpdateInventoryRequest;
import com.gabriel.ecommerce.dto.response.InventoryResponse;
import com.gabriel.ecommerce.entity.Inventory;
import com.gabriel.ecommerce.entity.ProductVariant;
import com.gabriel.ecommerce.exception.BusinessException;
import com.gabriel.ecommerce.exception.NotFoundException;
import com.gabriel.ecommerce.repository.InventoryRepository;
import com.gabriel.ecommerce.repository.ProductVariantRepository;
import com.gabriel.ecommerce.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;
    private final ProductVariantRepository productVariantRepository;

    @Override
    @Transactional
    public InventoryResponse create(CreateInventoryRequest request) {
        ProductVariant productVariant = getProductVariantById(
            request.productVariantId()
        );

        inventoryRepository
            .findByProductVariantId(request.productVariantId())
            .ifPresent(existingInventory -> {
                throw new BusinessException(
                    "Esta variante já possui um estoque cadastrado."
                );
            });

        Inventory inventory = Inventory.builder()
            .quantity(request.quantity())
            .reservedQuantity(0)
            .productVariant(productVariant)
            .build();

        Inventory savedInventory = inventoryRepository.save(inventory);

        return toResponse(savedInventory);
    }

    @Override
    @Transactional(readOnly = true)
    public List<InventoryResponse> findAll() {
        return inventoryRepository.findAll()
            .stream()
            .map(this::toResponse)
            .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public InventoryResponse findById(UUID id) {
        Inventory inventory = getInventoryById(id);

        return toResponse(inventory);
    }

    @Override
    @Transactional(readOnly = true)
    public InventoryResponse findByProductVariant(UUID productVariantId) {
        Inventory inventory = inventoryRepository
            .findByProductVariantId(productVariantId)
            .orElseThrow(() ->
                new NotFoundException(
                    "Estoque da variante não encontrado."
                )
            );

        return toResponse(inventory);
    }

    @Override
    @Transactional
    public InventoryResponse update(
        UUID id,
        UpdateInventoryRequest request
    ) {
        Inventory inventory = getInventoryById(id);

        if (request.quantity() < inventory.getReservedQuantity()) {
            throw new BusinessException(
                "A quantidade total não pode ser menor que a quantidade reservada."
            );
        }

        inventory.setQuantity(request.quantity());

        Inventory updatedInventory = inventoryRepository.save(inventory);

        return toResponse(updatedInventory);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        Inventory inventory = getInventoryById(id);

        if (inventory.getReservedQuantity() > 0) {
            throw new BusinessException(
                "Não é possível remover um estoque com itens reservados."
            );
        }

        inventoryRepository.delete(inventory);
    }

    private Inventory getInventoryById(UUID id) {
        return inventoryRepository.findById(id)
            .orElseThrow(() ->
                new NotFoundException("Estoque não encontrado.")
            );
    }

    private ProductVariant getProductVariantById(UUID id) {
        return productVariantRepository.findById(id)
            .orElseThrow(() ->
                new NotFoundException(
                    "Variante de produto não encontrada."
                )
            );
    }

    private InventoryResponse toResponse(Inventory inventory) {
        return new InventoryResponse(
            inventory.getId(),
            inventory.getQuantity(),
            inventory.getReservedQuantity(),
            inventory.getAvailableQuantity(),
            inventory.getProductVariant().getId(),
            inventory.getProductVariant().getSku(),
            inventory.getCreatedAt(),
            inventory.getUpdatedAt()
        );
    }
}