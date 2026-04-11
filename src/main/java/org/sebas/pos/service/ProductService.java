package org.sebas.pos.service;

import org.sebas.pos.dto.ProductDto;
import org.sebas.pos.exception.BadRequestException;
import org.sebas.pos.exception.ResourceAlreadyExistsException;
import org.sebas.pos.exception.ResourceNotFoundException;
import org.sebas.pos.mapper.ProductMapper;
import org.sebas.pos.model.Product;
import org.sebas.pos.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {
    private final ProductRepo productRepo;
    private final ProductMapper productMapper;

    @Autowired
    public ProductService(ProductRepo productRepo, ProductMapper productMapper) {
        this.productRepo = productRepo;
        this.productMapper = productMapper;
    }

    public Product createProduct(Product product) {
        if (product == null) {
            throw new BadRequestException("Product must not be null");
        }
        if (product.getProductName() == null || product.getProductName().isBlank()) {
            throw new BadRequestException("Product name is required");
        }
        if (product.getPrice() == null) {
            throw new BadRequestException("Product price is required");
        }
        if (product.getPrice().signum() < 0) {
            throw new BadRequestException("Product price must be >= 0");
        }
        // check duplicate by name
        Product existing = productRepo.findProductByProductName(product.getProductName());
        if (existing != null) {
            throw new ResourceAlreadyExistsException("Product with name '" + product.getProductName() + "' already exists");
        }

        return productRepo.save(product);
    }

    public void deleteProduct(UUID id) {
        Optional<Product> found = productRepo.findById(id);
        if (found.isEmpty()) {
            throw new ResourceNotFoundException("Product with id '" + id + "' not found");
        }
        productRepo.deleteById(id);
    }

    public ProductDto updateProduct(UUID id, ProductDto newProduct) {
        Optional<Product> productOptional = productRepo.findById(id);
        if (productOptional.isEmpty()) {
            throw new ResourceNotFoundException("Product with id '" + id + "' not found");
        }

        Product product = productOptional.get();

        product.setPrice(newProduct.getPrice());
        product.setProductName(newProduct.getProductName());
        product.setBarcode(newProduct.getBarcode());
        product.setStock(newProduct.getStock());
        product.setMinStock(newProduct.getMinStock());
        var finalProduct = productRepo.save(product);

        return productMapper.toDto(finalProduct);
    }

    public ProductDto findProductById(UUID id) {
        Optional<Product> productOptional = productRepo.findById(id);
        if (productOptional.isEmpty()){
            throw new ResourceNotFoundException("Product with id '" + id + "' not found");
        }

        return productMapper.toDto(productOptional.get());
    }

    public List<Product> getAllProducts(){
        return productRepo.findAll();
    }

}
