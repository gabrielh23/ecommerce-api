package com.gabriel.ecommerce.service;

import com.gabriel.ecommerce.dto.request.CreateProductRequest;
import com.gabriel.ecommerce.dto.request.UpdateProductRequest;
import com.gabriel.ecommerce.dto.response.ProductResponse;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    ProductResponse create(CreateProductRequest request);

    List<ProductResponse> findAll();

    ProductResponse findById(UUID id);

    ProductResponse update(UUID id, UpdateProductRequest request);

    void delete(UUID id);
}