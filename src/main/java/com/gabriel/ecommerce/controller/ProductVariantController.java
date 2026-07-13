package com.gabriel.ecommerce.controller;

import com.gabriel.ecommerce.dto.request.CreateProductVariantRequest;
import com.gabriel.ecommerce.dto.request.UpdateProductVariantRequest;
import com.gabriel.ecommerce.dto.response.ProductVariantResponse;
import com.gabriel.ecommerce.service.ProductVariantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/product-variants")
@RequiredArgsConstructor
public class ProductVariantController {

    private final ProductVariantService productVariantService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductVariantResponse create(
        @RequestBody @Valid CreateProductVariantRequest request
    ) {
        return productVariantService.create(request);
    }

    @GetMapping
    public List<ProductVariantResponse> findAll(
        @RequestParam(required = false) UUID productId
    ) {
        if (productId != null) {
            return productVariantService
                .findAllByProductId(productId);
        }

        return productVariantService.findAll();
    }

    @GetMapping("/{id}")
    public ProductVariantResponse findById(
            @PathVariable UUID id
    ) {
        return productVariantService.findById(id);
    }

    @PutMapping("/{id}")
    public ProductVariantResponse update(
            @PathVariable UUID id,
            @RequestBody @Valid UpdateProductVariantRequest request
    ) {
        return productVariantService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        productVariantService.delete(id);
    }
}