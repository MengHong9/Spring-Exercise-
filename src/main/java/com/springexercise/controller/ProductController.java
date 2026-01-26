package com.springexercise.controller;


import com.springexercise.common.response.Response;
import com.springexercise.dto.product.ProductDto;
import com.springexercise.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping
    public ResponseEntity<Response> getAllProducts() {
        return productService.getAllProducts();
    }



    @PostMapping
    public ResponseEntity<Response> addProduct(@Valid @RequestBody ProductDto payload) {
        return productService.createProduct(payload);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Response> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductDto payload) {
        return productService.updateProduct(id, payload);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteProduct(@PathVariable Long id) {
        return productService.deleteProduct(id);
    }

    @GetMapping("{id}")
    public ResponseEntity<Response> getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @GetMapping("/search")
    public ResponseEntity<Response> searchProduct(
            @RequestParam(value = "productName" , required = false) String productName,
            @RequestParam(value = "minPrice" , required = false) Double minPrice,
            @RequestParam(value = "maxPrice" , required = false) Double maxPrice
    )
    {
        return productService.searchProduct(productName, minPrice, maxPrice);
    }

}
