package com.gabriel.ecommerce.service;

import com.gabriel.ecommerce.dto.request.CreateCategoryRequest;
import com.gabriel.ecommerce.dto.request.UpdateCategoryRequest;
import com.gabriel.ecommerce.dto.response.CategoryResponse;

import java.util.List;
import java.util.UUID;

public interface CategoryService {

    CategoryResponse create(CreateCategoryRequest request);

    List<CategoryResponse> findAll();

    CategoryResponse findById(UUID id);

    CategoryResponse update(UUID id, UpdateCategoryRequest request);

    void delete(UUID id);
}