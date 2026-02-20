package com.springexercise.service;


import com.springexercise.common.response.Response;
import com.springexercise.dto.product.ProductDto;
import com.springexercise.dto.product.ProductResponseDto;
import com.springexercise.entity.Product;
import com.springexercise.exception.DuplicateException;
import com.springexercise.exception.ResourceNotFoundException;
import com.springexercise.mapper.ProductMapper;
import com.springexercise.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;


    public List<ProductResponseDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return productMapper.toDtoList(products);

    }


    public void createProduct(ProductDto dto) {
        if (productRepository.existsByProductName(dto.getProductName())){
            throw new DuplicateException("this product already exists with name "+dto.getProductName());
        }

        Product product = productMapper.toEntity(dto);
        productRepository.save(product);


    }


    public void updateProduct(Long id, ProductDto dto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(" product not found with id : "+id));


        if (productRepository.existsByProductName(dto.getProductName())){
            throw new DuplicateException("this product already exists");
        }

        productMapper.updateProduct(product,dto);
        productRepository.save(product);

    }


    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("product not found with id : "+id));

        productRepository.delete(product);

    }


    public ProductResponseDto getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("product not found with id : "+id));

        return productMapper.toDto(product);

    }

    public List<Product> searchProduct(String productName, Double minPrice, Double maxPrice) {
        String formatedName = productName != null ? productName.toLowerCase() : null;
        return productRepository.findAllByFilter(formatedName, minPrice, maxPrice);

    }


}
