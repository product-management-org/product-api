package com.product.service;

import com.product.dto.ProductApiDto;

import com.product.entity.ProductEntity;
import com.product.entity.UserEntity;
import com.product.exception.ErrorCode;
import com.product.exception.ProductException;
import com.product.mapper.ProductMapper;
import com.product.repository.IProductRepository;
import com.product.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService{

    @Autowired
    private IProductRepository productRepository;
    @Autowired
    private IUserRepository userRepository;

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

    @Override
    public ProductApiDto updateProduct(Long id, ProductApiDto productApiDto) {
        Optional<ProductEntity> product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new ProductException(ErrorCode.PRODUCT_NOT_FOUND, "product does not exist");
        }
        ProductEntity productToUpdate = ProductMapper.toUpdateEntity(productApiDto, product.get());
        ProductEntity updatedProduct = productRepository.saveAndFlush(productToUpdate);
        return ProductMapper.toDto(updatedProduct);
    }

    @Override
    public List<ProductApiDto> getProductsByUserId(Long useId) {
        Optional<UserEntity> user = userRepository.findById(useId);
        if (user.isEmpty()){
            throw new ProductException(ErrorCode.USER_NOT_FOUND, "user does not exist");
        }
        return productRepository
                .findByUser(user.get())
                .stream()
                .map(ProductMapper::toDto)
                .toList();
    }

    @Override
    public ProductApiDto attachProductToUser(Long userId, Long productId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        ProductEntity product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        product.setUser(user);
        ProductEntity attachedProduct = productRepository.saveAndFlush(product);
        return ProductMapper.toDto(attachedProduct);
    }

}
