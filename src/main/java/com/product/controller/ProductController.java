package com.product.controller;

import com.product.dto.ProductApiDto;
import com.product.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<ProductApiDto>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("product/{id}")
    public ResponseEntity<ProductApiDto> getProductById(@PathVariable Long id){
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable Long id){
        productService.deleteProductById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/product")
    public ResponseEntity<ProductApiDto> saveProduct(@RequestBody ProductApiDto productApiDto){
        return ResponseEntity.ok(productService.saveProduct(productApiDto));
    }

    @PutMapping("/updateProduct/{id}")
    public ResponseEntity<ProductApiDto> updateProduct(
            @PathVariable Long id,
            @RequestBody ProductApiDto productApiDto){
        return ResponseEntity.ok(productService.updateProduct(id, productApiDto));

    }

    @GetMapping("product/user/{userId}")
    public ResponseEntity<List<ProductApiDto>> getProductsByUserId(@PathVariable Long userId){
        return ResponseEntity.ok(productService.getProductsByUserId(userId));
    }


}
