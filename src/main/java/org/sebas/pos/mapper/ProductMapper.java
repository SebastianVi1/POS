package org.sebas.pos.mapper;

import org.sebas.pos.dto.ProductDto;
import org.sebas.pos.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductDto toDto(Product product){
        ProductDto dto = new ProductDto();
        dto.builder()
                .id(product.getId())
                .stock(product.getStock())
                .barcode(product.getBarcode())
                .price(product.getPrice())
                .productName(product.getProductName())
                .minStock(product.getMinStock())
                .build();

        return dto;
    }

    public Product toProduct(ProductDto dto){
        Product product = new Product();
        product.builder()
                .barcode(dto.getBarcode())
                .productName(dto.getProductName())
                .minStock(dto.getMinStock())
                .price(dto.getPrice())
                .productName(dto.getProductName())
                .stock(dto.getStock())
                .build();
        return product;
    }
}
