package org.sebas.pos.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;


    @Setter
    private String productName;

    @Setter
    private String barcode;

    @Setter
    private BigDecimal price;

    @Setter
    private Integer stock = 0;

    @Setter
    private Integer minStock;


}
