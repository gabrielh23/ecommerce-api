package com.gabriel.ecommerce.mapper;

import com.gabriel.ecommerce.dto.response.ProductImageResponse;
import com.gabriel.ecommerce.entity.ProductImage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductImageMapper {

    @Mapping(target = "productId", source = "product.id")
    @Mapping(target = "productName", source = "product.name")
    ProductImageResponse toResponse(ProductImage productImage);
}