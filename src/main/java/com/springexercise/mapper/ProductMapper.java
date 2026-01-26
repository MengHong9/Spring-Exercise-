package com.springexercise.mapper;

import com.springexercise.dto.product.ProductDto;
import com.springexercise.dto.product.ProductResponseDto;
import com.springexercise.entity.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    public Product toEntity(ProductDto dto){
        Product product = new Product();
        product.setProductName(dto.getProductName());
        product.setPrice(dto.getPrice());
        product.setDescription(dto.getDescription());
        return product;
    }

    public ProductResponseDto toDto(Product product){
        ProductResponseDto dto = new ProductResponseDto();
        dto.setId(product.getId());
        dto.setProductName(product.getProductName());
        dto.setPrice(product.getPrice());
        dto.setDescription(product.getDescription());
        dto.setCreatedAt(product.getCreatedAt());
        dto.setUpdatedAt(product.getUpdatedAt());
        return dto;
    }


    public List<ProductResponseDto> toDtoList(List<Product> products){
        if (products == null || products.isEmpty()){
            return new ArrayList<>();
        }

        return products.stream().map(this::toDto).collect(Collectors.toList());
    }

    public void updateProduct(Product product, ProductDto dto){
        product.setProductName(dto.getProductName());
        product.setPrice(dto.getPrice());
        product.setDescription(dto.getDescription());
    }
}
