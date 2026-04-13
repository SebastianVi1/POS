package org.sebas.pos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotNull(message = "Product name cannot be null")
    @NotBlank(message = "Product name cannot be blank")
    private String productName;

    @Setter
    private String barcode;

    @Setter
    @NotNull(message = "Product price cannot be null")
    @NotBlank(message = "Product price cannot be blank")
    private BigDecimal price;

    @Setter
    private Integer stock = 0;

    @Setter
    private Integer minStock;


}
