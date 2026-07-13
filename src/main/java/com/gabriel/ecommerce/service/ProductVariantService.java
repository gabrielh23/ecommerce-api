package com.gabriel.ecommerce.service;

import com.gabriel.ecommerce.dto.request.CreateProductVariantRequest;
import com.gabriel.ecommerce.dto.request.UpdateProductVariantRequest;
import com.gabriel.ecommerce.dto.response.ProductVariantResponse;

import java.util.List;
import java.util.UUID;

public interface ProductVariantService {

    ProductVariantResponse create(CreateProductVariantRequest request);

    List<ProductVariantResponse> findAll();

    List<ProductVariantResponse> findAllByProductId(UUID productId);

    ProductVariantResponse findById(UUID id);

    ProductVariantResponse update(
        UUID id,
        UpdateProductVariantRequest request
    );

    void delete(UUID id);
}