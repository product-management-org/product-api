package com.product.service;

import com.product.dto.ProductApiDto;

import java.util.List;

public interface IProductService {

    List<ProductApiDto> getAllProducts();
    ProductApiDto getProductById(Long productId);
}
