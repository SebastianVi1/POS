package org.sebas.pos.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
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
    private int stock;

    @Setter
    private int minStock;


}
