package org.sebas.pos.repo;

import org.sebas.pos.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepo extends JpaRepository<Product, UUID> {
    Product findProductByProductName(String productName);
}
