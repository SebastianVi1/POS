package org.sebas.pos.controller;

import jakarta.validation.Valid;
import org.sebas.pos.dto.ProductDto;
import org.sebas.pos.model.Product;
import org.sebas.pos.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;
import java.util.List;

@RestController()
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    ProductService productService;


    @PostMapping()
    public ResponseEntity<Product> createProduct(@RequestBody @Valid Product product){
        Product result = productService.createProduct(product);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> productList = productService.getAllProducts();
        return ResponseEntity.ok(productList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> findProductById(@PathVariable UUID id){
        return ResponseEntity.ok(productService.findProductById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable UUID id, @RequestBody @Valid ProductDto product){
        return new ResponseEntity<>(productService.updateProduct(id, product), HttpStatus.CREATED);
    }
}
