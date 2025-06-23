package com.product.mapper;

import com.product.dto.ProductApiDto;
import com.product.entity.ProductEntity;

import java.time.Instant;
import java.time.ZoneOffset;

public class ProductMapper {

    public static ProductEntity toNewEntity(ProductApiDto dto){
        ProductEntity product = new ProductEntity();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setSku(dto.getSku());
        product.setCategory(dto.getCategory());
        product.setCreatedAt(Instant.now());
        product.setUpdatedAt(Instant.now());
        return product;
    }

    public static ProductEntity toUpdateEntity(ProductApiDto dto, ProductEntity product){
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setSku(dto.getSku());
        product.setCategory(dto.getCategory());
        product.setUpdatedAt(Instant.now());
        return product;
    }

    public static ProductApiDto toDto(ProductEntity entity){
        ProductApiDto dto = new ProductApiDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setPrice(entity.getPrice());
        dto.setSku(entity.getSku());
        dto.setCategory(entity.getCategory());
        dto.setCreatedAt(entity.getCreatedAt().atOffset(ZoneOffset.UTC));
        dto.setUpdatedAt(entity.getUpdatedAt().atOffset(ZoneOffset.UTC));
        return dto;
    }
}
