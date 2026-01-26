package com.springexercise.repository;

import com.springexercise.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByProductName(String productName);


    @Query("SELECT p FROM Product p WHERE" +
            "(:productName IS NULL OR LOWER(p.productName) LIKE %:productName%) AND" +
            "(:minPrice IS NULL OR p.price >= :minPrice) AND" +
            "(:maxPrice IS NULL OR p.price <= :maxPrice)"
    )
    List<Product> findAllByFilter(
            @Param("productName") String productName,
            @Param("minPrice") Double minPrice,
            @Param("maxPrice") Double maxPrice
    );
}


