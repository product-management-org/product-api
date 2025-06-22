package com.product.service;

import com.product.dto.ProductApiDto;

import com.product.entity.ProductEntity;
import com.product.exception.ErrorCode;
import com.product.exception.ProductException;
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
            throw new ProductException(ErrorCode.PRODUCT_NOT_FOUND, "product does not exist");
        }
        return ProductMapper.toDto(product.get());
    }

    @Override
    public void deleteProductById(Long id) {
        Optional<ProductEntity> product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new ProductException(ErrorCode.PRODUCT_NOT_FOUND, "product does not exist");
        }
        productRepository.deleteById(id);
    }

    @Override
    public ProductApiDto saveProduct(ProductApiDto productApiDto) {
        Optional<ProductEntity> product = productRepository.findBySku(productApiDto.getSku());
        if (product.isPresent()) {
            throw new ProductException(ErrorCode.PRODUCT_ALREADY_EXISTS, "product already exists");
        }
        ProductEntity productToSave = ProductMapper.toNewEntity(productApiDto);
        ProductEntity savedProduct = productRepository.save(productToSave);
        return ProductMapper.toDto(savedProduct);
    }

}
