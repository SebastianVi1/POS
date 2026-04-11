package org.sebas.pos.dto;

import lombok.*;
import org.sebas.pos.model.Product;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {
    private UUID id;

    private String productName;

    private String barcode;

    private BigDecimal price;

    private int stock;

    private int minStock;
}
