package com.product;

import com.product.dto.ProductApiDto;
import com.product.entity.ProductEntity;
import com.product.exception.ProductException;
import com.product.mapper.ProductMapper;
import com.product.repository.IProductRepository;
import com.product.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private IProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private ProductEntity product1;
    private ProductEntity product2;

    @BeforeEach
    public void setup(){
        product1 = new ProductEntity();
        product1.setId(1L);
        product1.setName("Wireless Mouse");
        product1.setDescription("Ergonomic wireless mouse");
        product1.setPrice(BigDecimal.valueOf(25.11));
        product1.setSku("WM-1002");
        product1.setCategory("Electronics");
        product1.setCreatedAt(Instant.now());
        product1.setUpdatedAt(Instant.now());

        product2 = new ProductEntity();
        product2.setId(2L);
        product2.setName("Computer");
        product2.setDescription("Ergonomic wireless mouse");
        product2.setPrice(BigDecimal.valueOf(500));
        product2.setSku("WM-1004");
        product2.setCategory("Electronics");
        product1.setCreatedAt(Instant.now());
        product1.setUpdatedAt(Instant.now());
    }

    @Test
    void test_getAllProducts(){
        List<ProductEntity> listProducts = Arrays.asList(product1, product2);
        when(productRepository.findAll()).thenReturn(listProducts);
        List<ProductApiDto> result = productService.getAllProducts();
        assertThat(result.getFirst().getId()).isEqualTo(1L);
        assertThat(result.getFirst().getName()).isEqualTo("Wireless Mouse");
        assertThat(result).hasSize(2);
    }

    @Test
    void test_getProductById_when_id_is_valid(){
        Optional<ProductEntity> product = Optional.of(product2);
        when(productRepository.findById(2L)).thenReturn(product);
        ProductApiDto result = productService.getProductById(2L);
        assertThat(result.getId()).isEqualTo(2L);
        assertThat(result.getName()).isEqualTo("Computer");
    }

    @Test
    void test_getProductById_when_id_does_not_exist(){
        when(productRepository.findById(88L)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> productService.getProductById(88L))
                .isInstanceOf(ProductException.class)
                .hasMessageContaining("product does not exist");
    }

    @Test
    void test_deleteProductById_when_id_is_valid(){
        Optional<ProductEntity> product = Optional.of(product1);
        when(productRepository.findById(1L)).thenReturn(product);
        doNothing().when(productRepository).deleteById(1L);
        productService.deleteProductById(1L);
        verify(productRepository, times(1)).deleteById(1L);
    }

    @Test
    void test_deleteProductById_when_id_is_not_valid(){
        when(productRepository.findById(90L)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> productService.deleteProductById(90L))
                .isInstanceOf(ProductException.class)
                .hasMessageContaining("product does not exist");
    }

    @Test
    void test_saveProduct_when_sku_does_not_exist() {
        ProductApiDto dto = ProductMapper.toDto(product1);
        when(productRepository.findBySku(anyString())).thenReturn(Optional.empty());
        when(productRepository.save(any(ProductEntity.class))).thenReturn(product1);
        ProductApiDto result = productService.saveProduct(dto);
        assertThat(result).isNotNull();
        assertThat(result.getSku()).isEqualTo("WM-1002");
        assertThat(result.getName()).isEqualTo("Wireless Mouse");
    }

    @Test
    void test_saveProduct_when_sku_already_exists() {
        when(productRepository.findBySku("WM-1002")).thenReturn(Optional.of(product1));
        ProductApiDto dto = ProductMapper.toDto(product1);
        assertThatThrownBy(() -> productService.saveProduct(dto))
                .isInstanceOf(ProductException.class)
                .hasMessageContaining("product already exists");
    }

}
