package com.gabriel.ecommerce.mapper;

import com.gabriel.ecommerce.dto.response.ProductResponse;
import com.gabriel.ecommerce.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(
        target = "categoryId",
        source = "category.id"
    )
    @Mapping(
        target = "categoryName",
        source = "category.name"
    )
    ProductResponse toResponse(Product product);
}