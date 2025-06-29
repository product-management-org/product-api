package com.product.service;

import com.product.dto.ProductApiDto;

import java.util.List;

public interface IProductService {

    List<ProductApiDto> getAllProducts();
    ProductApiDto getProductById(Long id);
    void deleteProductById(Long id);
    ProductApiDto saveProduct(ProductApiDto productApiDto);
    ProductApiDto updateProduct(Long id, ProductApiDto productApiDto);
    List<ProductApiDto> getProductsByUserId(Long useId);
    ProductApiDto attachProductToUser(Long userId, Long productId);
}
