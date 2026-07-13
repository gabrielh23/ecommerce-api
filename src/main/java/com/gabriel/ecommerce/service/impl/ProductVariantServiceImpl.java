package com.gabriel.ecommerce.service.impl;

import com.gabriel.ecommerce.dto.request.CreateProductVariantRequest;
import com.gabriel.ecommerce.dto.request.UpdateProductVariantRequest;
import com.gabriel.ecommerce.dto.response.ProductVariantResponse;
import com.gabriel.ecommerce.entity.Product;
import com.gabriel.ecommerce.entity.ProductVariant;
import com.gabriel.ecommerce.exception.BusinessException;
import com.gabriel.ecommerce.exception.NotFoundException;
import com.gabriel.ecommerce.repository.ProductRepository;
import com.gabriel.ecommerce.repository.ProductVariantRepository;
import com.gabriel.ecommerce.service.ProductVariantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductVariantServiceImpl
        implements ProductVariantService {

    private final ProductVariantRepository productVariantRepository;
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public ProductVariantResponse create(
        CreateProductVariantRequest request
    ) {
        String normalizedSku = normalizeSku(request.sku());

        if (productVariantRepository.existsBySku(normalizedSku)) {
            throw new BusinessException("SKU já cadastrado.");
        }

        Product product = getProductById(request.productId());

        ProductVariant variant = ProductVariant.builder()
            .sku(normalizedSku)
            .color(request.color().trim())
            .size(request.size().trim())
            .price(request.price())
            .active(true)
            .product(product)
            .build();

        ProductVariant savedVariant =
            productVariantRepository.save(variant);

        return toResponse(savedVariant);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductVariantResponse> findAll() {
        return productVariantRepository.findAll()
            .stream()
            .map(this::toResponse)
            .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductVariantResponse> findAllByProductId(
        UUID productId
    ) {
        getProductById(productId);

        return productVariantRepository
            .findAllByProductId(productId)
            .stream()
            .map(this::toResponse)
            .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ProductVariantResponse findById(UUID id) {
        return toResponse(getVariantById(id));
    }

    @Override
    @Transactional
    public ProductVariantResponse update(
        UUID id,
        UpdateProductVariantRequest request
    ) {
        ProductVariant variant = getVariantById(id);
        String normalizedSku = normalizeSku(request.sku());

        productVariantRepository.findBySku(normalizedSku)
            .filter(existing -> !existing.getId().equals(id))
            .ifPresent(existing -> {
                throw new BusinessException("SKU já cadastrado.");
            });

        Product product = getProductById(request.productId());

        variant.setSku(normalizedSku);
        variant.setColor(request.color().trim());
        variant.setSize(request.size().trim());
        variant.setPrice(request.price());
        variant.setProduct(product);

        if (request.active() != null) {
            variant.setActive(request.active());
        }

        ProductVariant updatedVariant =
            productVariantRepository.save(variant);

        return toResponse(updatedVariant);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        ProductVariant variant = getVariantById(id);
        variant.setActive(false);
        productVariantRepository.save(variant);
    }

    private ProductVariant getVariantById(UUID id) {
        return productVariantRepository.findById(id)
            .orElseThrow(() ->
                new NotFoundException(
                    "Variante de produto não encontrada."
                )
            );
    }

    private Product getProductById(UUID id) {
        return productRepository.findById(id)
            .orElseThrow(() ->
                new NotFoundException(
                    "Produto não encontrado."
                )
            );
    }

    private ProductVariantResponse toResponse(
        ProductVariant variant
    ) {
        return new ProductVariantResponse(
            variant.getId(),
            variant.getSku(),
            variant.getColor(),
            variant.getSize(),
            variant.getPrice(),
            variant.getActive(),
            variant.getProduct().getId(),
            variant.getProduct().getName(),
            variant.getCreatedAt(),
            variant.getUpdatedAt()
        );
    }

    private String normalizeSku(String sku) {
        return sku.trim().toUpperCase();
    }
}