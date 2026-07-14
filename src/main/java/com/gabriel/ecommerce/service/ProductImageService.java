package com.gabriel.ecommerce.service;

import com.gabriel.ecommerce.dto.request.CreateProductImageRequest;
import com.gabriel.ecommerce.dto.request.UpdateProductImageRequest;
import com.gabriel.ecommerce.dto.response.ProductImageResponse;

import java.util.List;
import java.util.UUID;

public interface ProductImageService {

    ProductImageResponse create(CreateProductImageRequest request);

    List<ProductImageResponse> findAllByProductId(UUID productId);

    ProductImageResponse findById(UUID id);

    ProductImageResponse update(
            UUID id,
            UpdateProductImageRequest request
    );

    void delete(UUID id);
}