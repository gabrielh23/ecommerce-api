package com.gabriel.ecommerce.service.impl;

import com.gabriel.ecommerce.dto.request.CreateProductImageRequest;
import com.gabriel.ecommerce.dto.request.LoginRequest;
import com.gabriel.ecommerce.dto.request.UpdateProductImageRequest;
import com.gabriel.ecommerce.dto.response.LoginResponse;
import com.gabriel.ecommerce.dto.response.ProductImageResponse;
import com.gabriel.ecommerce.entity.Product;
import com.gabriel.ecommerce.entity.ProductImage;
import com.gabriel.ecommerce.entity.User;
import com.gabriel.ecommerce.exception.BusinessException;
import com.gabriel.ecommerce.exception.NotFoundException;
import com.gabriel.ecommerce.exception.UnauthorizedException;
import com.gabriel.ecommerce.mapper.ProductImageMapper;
import com.gabriel.ecommerce.repository.ProductImageRepository;
import com.gabriel.ecommerce.repository.ProductRepository;
import com.gabriel.ecommerce.repository.UserRepository;
import com.gabriel.ecommerce.security.JwtService;
import com.gabriel.ecommerce.service.AuthService;
import com.gabriel.ecommerce.service.ProductImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.email())
            .orElseThrow(() -> new UnauthorizedException("E-mail ou senha inválidos."));

        boolean passwordMatches = passwordEncoder.matches(request.password(), user.getPassword());

        if (!passwordMatches) {
            throw new RuntimeException("E-mail ou senha inválidos.");
        }

        String token = jwtService.generateToken(user);

        return new LoginResponse(token);
    }

    @Service
    @RequiredArgsConstructor
    public static class ProductImageServiceImpl implements ProductImageService {

        private final ProductImageRepository productImageRepository;
        private final ProductRepository productRepository;
        private final ProductImageMapper productImageMapper;

        @Override
        @Transactional
        public ProductImageResponse create(
                CreateProductImageRequest request
        ) {
            Product product = getProductById(request.productId());

            String normalizedUrl = request.url().trim();

            if (productImageRepository.existsByProductIdAndUrl(
                    request.productId(),
                    normalizedUrl
            )) {
                throw new BusinessException(
                        "Esta imagem já está cadastrada para o produto."
                );
            }

            if (request.mainImage()) {
                removeCurrentMainImage(request.productId());
            }

            ProductImage productImage = ProductImage.builder()
                    .url(normalizedUrl)
                    .altText(normalizeOptionalText(request.altText()))
                    .position(request.position())
                    .mainImage(request.mainImage())
                    .product(product)
                    .build();

            ProductImage savedImage =
                    productImageRepository.save(productImage);

            return productImageMapper.toResponse(savedImage);
        }

        @Override
        @Transactional(readOnly = true)
        public List<ProductImageResponse> findAllByProductId(
                UUID productId
        ) {
            getProductById(productId);

            return productImageRepository
                    .findAllByProductIdOrderByPositionAsc(productId)
                    .stream()
                    .map(productImageMapper::toResponse)
                    .toList();
        }

        @Override
        @Transactional(readOnly = true)
        public ProductImageResponse findById(UUID id) {
            ProductImage productImage = getProductImageById(id);

            return productImageMapper.toResponse(productImage);
        }

        @Override
        @Transactional
        public ProductImageResponse update(
                UUID id,
                UpdateProductImageRequest request
        ) {
            ProductImage productImage = getProductImageById(id);
            String normalizedUrl = request.url().trim();

            productImageRepository
                    .findAllByProductIdOrderByPositionAsc(
                            productImage.getProduct().getId()
                    )
                    .stream()
                    .filter(existing ->
                            existing.getUrl().equals(normalizedUrl)
                                    && !existing.getId().equals(id)
                    )
                    .findFirst()
                    .ifPresent(existing -> {
                        throw new BusinessException(
                                "Esta imagem já está cadastrada para o produto."
                        );
                    });

            if (request.mainImage()) {
                removeCurrentMainImage(
                        productImage.getProduct().getId(),
                        productImage.getId()
                );
            }

            productImage.setUrl(normalizedUrl);
            productImage.setAltText(
                    normalizeOptionalText(request.altText())
            );
            productImage.setPosition(request.position());
            productImage.setMainImage(request.mainImage());

            ProductImage updatedImage =
                    productImageRepository.save(productImage);

            return productImageMapper.toResponse(updatedImage);
        }

        @Override
        @Transactional
        public void delete(UUID id) {
            ProductImage productImage = getProductImageById(id);

            productImageRepository.delete(productImage);
        }

        private ProductImage getProductImageById(UUID id) {
            return productImageRepository.findById(id)
                    .orElseThrow(() ->
                            new NotFoundException(
                                    "Imagem do produto não encontrada."
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

        private void removeCurrentMainImage(UUID productId) {
            productImageRepository
                    .findByProductIdAndMainImageTrue(productId)
                    .ifPresent(currentMainImage -> {
                        currentMainImage.setMainImage(false);
                        productImageRepository.save(currentMainImage);
                    });
        }

        private void removeCurrentMainImage(
                UUID productId,
                UUID imageToIgnoreId
        ) {
            productImageRepository
                    .findByProductIdAndMainImageTrue(productId)
                    .filter(currentMainImage ->
                            !currentMainImage
                                    .getId()
                                    .equals(imageToIgnoreId)
                    )
                    .ifPresent(currentMainImage -> {
                        currentMainImage.setMainImage(false);
                        productImageRepository.save(currentMainImage);
                    });
        }

        private String normalizeOptionalText(String value) {
            if (value == null || value.isBlank()) {
                return null;
            }

            return value.trim();
        }
    }
}