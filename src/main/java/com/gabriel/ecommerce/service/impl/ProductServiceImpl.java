package com.gabriel.ecommerce.service.impl;

import com.gabriel.ecommerce.dto.request.CreateProductRequest;
import com.gabriel.ecommerce.dto.request.UpdateProductRequest;
import com.gabriel.ecommerce.dto.response.ProductResponse;
import com.gabriel.ecommerce.entity.Category;
import com.gabriel.ecommerce.entity.Product;
import com.gabriel.ecommerce.exception.BusinessException;
import com.gabriel.ecommerce.exception.NotFoundException;
import com.gabriel.ecommerce.mapper.ProductMapper;
import com.gabriel.ecommerce.repository.CategoryRepository;
import com.gabriel.ecommerce.repository.ProductRepository;
import com.gabriel.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.Normalizer;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    @Override
    @Transactional
    public ProductResponse create(CreateProductRequest request) {
        String slug = generateSlug(request.name());

        if (productRepository.existsBySlug(slug)) {
            throw new BusinessException("Produto já cadastrado.");
        }

        Category category = getCategoryById(request.categoryId());

        Product product = Product.builder()
                .name(request.name().trim())
                .slug(slug)
                .description(request.description())
                .price(request.price())
                .active(true)
                .category(category)
                .build();

        Product savedProduct = productRepository.save(product);

        return productMapper.toResponse(savedProduct);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductResponse> findAll() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ProductResponse findById(UUID id) {
        Product product = getProductById(id);

        return productMapper.toResponse(product);
    }

    @Override
    @Transactional
    public ProductResponse update(
            UUID id,
            UpdateProductRequest request
    ) {
        Product product = getProductById(id);
        String newSlug = generateSlug(request.name());

        productRepository.findBySlug(newSlug)
                .filter(existing -> !existing.getId().equals(id))
                .ifPresent(existing -> {
                    throw new BusinessException(
                            "Produto já cadastrado."
                    );
                });

        Category category = getCategoryById(request.categoryId());

        product.setName(request.name().trim());
        product.setSlug(newSlug);
        product.setDescription(request.description());
        product.setPrice(request.price());
        product.setCategory(category);

        if (request.active() != null) {
            product.setActive(request.active());
        }

        Product updatedProduct = productRepository.save(product);

        return productMapper.toResponse(updatedProduct);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        Product product = getProductById(id);

        product.setActive(false);

        productRepository.save(product);
    }

    private Product getProductById(UUID id) {
        return productRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException(
                                "Produto não encontrado."
                        )
                );
    }

    private Category getCategoryById(UUID id) {
        return categoryRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException(
                                "Categoria não encontrada."
                        )
                );
    }

    private String generateSlug(String value) {
        String normalized = Normalizer.normalize(
                        value,
                        Normalizer.Form.NFD
                )
                .replaceAll("\\p{M}", "");

        return normalized
                .toLowerCase()
                .trim()
                .replaceAll("[^a-z0-9\\s-]", "")
                .replaceAll("\\s+", "-")
                .replaceAll("-+", "-");
    }
}