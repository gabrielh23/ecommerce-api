package com.gabriel.ecommerce.controller;

import com.gabriel.ecommerce.dto.request.CreateProductImageRequest;
import com.gabriel.ecommerce.dto.request.UpdateProductImageRequest;
import com.gabriel.ecommerce.dto.response.ProductImageResponse;
import com.gabriel.ecommerce.service.ProductImageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/product-images")
@RequiredArgsConstructor
public class ProductImageController {

    private final ProductImageService productImageService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductImageResponse create(
            @RequestBody @Valid CreateProductImageRequest request
    ) {
        return productImageService.create(request);
    }

    @GetMapping
    public List<ProductImageResponse> findAllByProductId(
            @RequestParam UUID productId
    ) {
        return productImageService.findAllByProductId(productId);
    }

    @GetMapping("/{id}")
    public ProductImageResponse findById(
            @PathVariable UUID id
    ) {
        return productImageService.findById(id);
    }

    @PutMapping("/{id}")
    public ProductImageResponse update(
            @PathVariable UUID id,
            @RequestBody @Valid UpdateProductImageRequest request
    ) {
        return productImageService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @PathVariable UUID id
    ) {
        productImageService.delete(id);
    }
}