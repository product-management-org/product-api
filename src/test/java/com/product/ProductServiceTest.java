package com.product;

import com.product.dto.ProductApiDto;
import com.product.entity.ProductEntity;
import com.product.repository.IProductRepository;
import com.product.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

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

        product2 = new ProductEntity();
        product2.setId(2L);
        product2.setName("Computer");
        product2.setDescription("Ergonomic wireless mouse");
        product2.setPrice(BigDecimal.valueOf(500));
        product2.setSku("WM-1004");
        product2.setCategory("Electronics");
    }

    @Test
    void get_all_products(){

        List<ProductEntity> listProducts = Arrays.asList(product1, product2);
        when(productRepository.findAll()).thenReturn(listProducts);
        List<ProductApiDto> result = productService.getAllProducts();
        assertThat(result.getFirst().getId()).isEqualTo(1L);
        assertThat(result.getFirst().getName()).isEqualTo("Wireless Mouse");
        assertThat(result).hasSize(2);

    }


}
