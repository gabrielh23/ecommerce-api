package com.gabriel.ecommerce.controller;

import com.gabriel.ecommerce.dto.request.CreateInventoryRequest;
import com.gabriel.ecommerce.dto.request.UpdateInventoryRequest;
import com.gabriel.ecommerce.dto.response.InventoryResponse;
import com.gabriel.ecommerce.service.InventoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/inventories")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InventoryResponse create(
            @RequestBody @Valid CreateInventoryRequest request
    ) {
        return inventoryService.create(request);
    }

    @GetMapping
    public List<InventoryResponse> findAll() {
        return inventoryService.findAll();
    }

    @GetMapping("/{id}")
    public InventoryResponse findById(
            @PathVariable UUID id
    ) {
        return inventoryService.findById(id);
    }

    @GetMapping("/variant/{productVariantId}")
    public InventoryResponse findByProductVariant(
            @PathVariable UUID productVariantId
    ) {
        return inventoryService.findByProductVariant(productVariantId);
    }

    @PutMapping("/{id}")
    public InventoryResponse update(
            @PathVariable UUID id,
            @RequestBody @Valid UpdateInventoryRequest request
    ) {
        return inventoryService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @PathVariable UUID id
    ) {
        inventoryService.delete(id);
    }
}