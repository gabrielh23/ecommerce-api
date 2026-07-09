package com.gabriel.ecommerce.service.impl;

import com.gabriel.ecommerce.dto.request.CreateCategoryRequest;
import com.gabriel.ecommerce.dto.request.UpdateCategoryRequest;
import com.gabriel.ecommerce.dto.response.CategoryResponse;
import com.gabriel.ecommerce.entity.Category;
import com.gabriel.ecommerce.exception.BusinessException;
import com.gabriel.ecommerce.exception.NotFoundException;
import com.gabriel.ecommerce.repository.CategoryRepository;
import com.gabriel.ecommerce.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public CategoryResponse create(CreateCategoryRequest request) {
        String slug = generateSlug(request.name());

        if (categoryRepository.existsBySlug(slug)) {
            throw new BusinessException("Categoria já cadastrada.");
        }

        Category category = Category.builder()
            .name(request.name())
            .slug(slug)
            .description(request.description())
            .active(true)
            .build();

        return toResponse(categoryRepository.save(category));
    }

    @Override
    public List<CategoryResponse> findAll() {
        return categoryRepository.findAll()
            .stream()
            .map(this::toResponse)
            .toList();
    }

    @Override
    public CategoryResponse findById(UUID id) {
        return toResponse(getCategoryById(id));
    }

    @Override
    public CategoryResponse update(UUID id, UpdateCategoryRequest request) {
        Category category = getCategoryById(id);

        String newSlug = generateSlug(request.name());

        categoryRepository.findBySlug(newSlug)
            .filter(existing -> !existing.getId().equals(id))
            .ifPresent(existing -> {
                throw new BusinessException("Categoria já cadastrada.");
            });

        category.setName(request.name());
        category.setSlug(newSlug);
        category.setDescription(request.description());

        if (request.active() != null) {
            category.setActive(request.active());
        }

        return toResponse(categoryRepository.save(category));
    }

    @Override
    public void delete(UUID id) {
        Category category = getCategoryById(id);
        category.setActive(false);
        categoryRepository.save(category);
    }

    private Category getCategoryById(UUID id) {
        return categoryRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Categoria não encontrada."));
    }

    private CategoryResponse toResponse(Category category) {
        return new CategoryResponse(
            category.getId(),
            category.getName(),
            category.getSlug(),
            category.getDescription(),
            category.getActive(),
            category.getCreatedAt(),
            category.getUpdatedAt()
        );
    }

    private String generateSlug(String value) {
        String normalized = Normalizer.normalize(value, Normalizer.Form.NFD)
            .replaceAll("\\p{M}", "");

        return normalized
            .toLowerCase()
            .trim()
            .replaceAll("[^a-z0-9\\s-]", "")
            .replaceAll("\\s+", "-")
            .replaceAll("-+", "-");
    }
}