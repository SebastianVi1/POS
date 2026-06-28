package org.sebas.pos.features.product.mapper;

import org.sebas.pos.features.product.domain.Product;
import org.springframework.stereotype.Component;
import org.sebas.pos.features.product.dto.ProductDto;

@Component
public class ProductMapper {

    public ProductDto toDto(Product product){
        return ProductDto.builder()
                .id(product.getId())
                .stock(product.getStock())
                .barcode(product.getBarcode())
                .price(product.getPrice())
                .productName(product.getProductName())
                .minStock(product.getMinStock())
                .build();
    }

    public Product toProduct(ProductDto dto){
        return Product.builder()
                .barcode(dto.getBarcode())
                .productName(dto.getProductName())
                .minStock(dto.getMinStock())
                .price(dto.getPrice())
                .stock(dto.getStock())
                .build();
    }
}
