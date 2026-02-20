package com.springexercise.controller;


import com.springexercise.common.response.Response;
import com.springexercise.dto.product.ProductDto;
import com.springexercise.dto.product.ProductResponseDto;
import com.springexercise.entity.Product;
import com.springexercise.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping
    public ResponseEntity<Response> getAllProducts() {
        List<ProductResponseDto> dtos = productService.getAllProducts();
        return ResponseEntity.status(HttpStatus.OK).body(Response.success("200","success" , "successfully retrieved product" , dtos));
    }



    @PostMapping
    public ResponseEntity<Response> addProduct(@Valid @RequestBody ProductDto payload) {
        productService.createProduct(payload);

        return ResponseEntity.status(HttpStatus.CREATED).body(Response.success("201","success" , "successfully added product "));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Response> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductDto payload) {
         productService.updateProduct(id, payload);
        return ResponseEntity.status(HttpStatus.OK).body(Response.success("success" , "successfully updated product "));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteProduct(@PathVariable Long id) {
         productService.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.OK).body(Response.success("success" , "successfully deleted product "));
    }

    @GetMapping("{id}")
    public ResponseEntity<Response> getProductById(@PathVariable Long id) {
        ProductResponseDto product = productService.getProductById(id);
        return ResponseEntity.status(HttpStatus.OK).body(Response.success("200","success" , "successfully retrieved product by id" , product));
    }

    @GetMapping("/search")
    public ResponseEntity<Response> searchProduct(
            @RequestParam(value = "productName" , required = false) String productName,
            @RequestParam(value = "minPrice" , required = false) Double minPrice,
            @RequestParam(value = "maxPrice" , required = false) Double maxPrice
    )
    {
        List<Product> products =  productService.searchProduct(productName, minPrice, maxPrice);
        return ResponseEntity.status(HttpStatus.OK).body(Response.success("200","success" , "successfully retrieved product " , products));
    }

}
