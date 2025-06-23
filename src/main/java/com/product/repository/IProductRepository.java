package com.product.repository;

import com.product.entity.ProductEntity;
import com.product.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IProductRepository extends JpaRepository<ProductEntity, Long> {

    Optional<ProductEntity> findBySku(String sku);
    List<ProductEntity> findByUser(UserEntity userEntity);



}
