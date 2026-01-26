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


    public ResponseEntity<Response> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductResponseDto> dtos = productMapper.toDtoList(products);

        return ResponseEntity.status(HttpStatus.OK).body(Response.success("200","success" , "successfully retrieved product" , dtos));
    }


    public ResponseEntity<Response> createProduct(ProductDto dto) {
        if (productRepository.existsByProductName(dto.getProductName())){
            throw new DuplicateException("this product already exists with name "+dto.getProductName());
        }

        Product product = productMapper.toEntity(dto);
        productRepository.save(product);

        return ResponseEntity.status(HttpStatus.CREATED).body(Response.success("201","success" , "successfully added product "));
    }


    public ResponseEntity<Response> updateProduct(Long id, ProductDto dto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(" product not found with id : "+id));

        productMapper.updateProduct(product,dto);
        productRepository.save(product);
        return ResponseEntity.status(HttpStatus.OK).body(Response.success("success" , "successfully updated product "));
    }


    public ResponseEntity<Response> deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("product not foumd with id : "+id));

        productRepository.delete(product);
        return ResponseEntity.status(HttpStatus.OK).body(Response.success("success" , "successfully deleted product "));
    }


    public ResponseEntity<Response> getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("product not found with id : "+id));

        return ResponseEntity.status(HttpStatus.OK).body(Response.success("200","success" , "successfully retrieved product " , product));

    }

    public ResponseEntity<Response> searchProduct(String productName, Double minPrice, Double maxPrice) {
        String formatedName = productName != null ? productName.toLowerCase() : null;
        List<Product> products = productRepository.findAllByFilter(formatedName, minPrice, maxPrice);
        return ResponseEntity.status(HttpStatus.OK).body(Response.success("200","success" , "successfully retrieved product " , products));
    }


}
