package org.sebas.pos.features.product.controller;

import jakarta.validation.Valid;
import org.sebas.pos.features.product.dto.ProductDto;
import org.sebas.pos.features.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.List;

@RestController()
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    ProductService productService;


    @PostMapping()
    public ResponseEntity<ProductDto> createProduct(@RequestBody @Valid ProductDto product){
        return new ResponseEntity<>(productService.createProduct(product), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        return ResponseEntity.ok(productService.getAllProducts());
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
