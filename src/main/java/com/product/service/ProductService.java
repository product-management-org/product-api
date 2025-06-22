package com.product.service;

import com.product.dto.ProductApiDto;

import com.product.entity.ProductEntity;
import com.product.mapper.ProductMapper;
import com.product.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService{

    @Autowired
    private IProductRepository productRepository;

    @Override
    public List<ProductApiDto> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(ProductMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductApiDto getProductById(Long id) {
        Optional<ProductEntity> product = productRepository.findById(id);
        if (product.isEmpty()){
            throw new RuntimeException("product does not exist");
        }
        return ProductMapper.toDto(product.get());
    }
}
