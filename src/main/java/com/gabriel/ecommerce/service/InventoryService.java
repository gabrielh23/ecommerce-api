package com.gabriel.ecommerce.service;

import com.gabriel.ecommerce.dto.request.CreateInventoryRequest;
import com.gabriel.ecommerce.dto.request.UpdateInventoryRequest;
import com.gabriel.ecommerce.dto.response.InventoryResponse;

import java.util.List;
import java.util.UUID;

public interface InventoryService {

    InventoryResponse create(CreateInventoryRequest request);

    List<InventoryResponse> findAll();

    InventoryResponse findById(UUID id);

    InventoryResponse findByProductVariant(UUID productVariantId);

    InventoryResponse update(UUID id, UpdateInventoryRequest request);

    void delete(UUID id);

}