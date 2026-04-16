package org.sebas.pos.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {
    private UUID id;

    @NotNull(message = "Product name cannot be null")
    @NotBlank(message = "Product name cannot be blank")
    @Size(min = 2, max = 100, message = "Product name must be between 2 and 100 characters")
    private String productName;

    @Size(max = 50, message = "Barcode cannot exceed 50 characters")
    private String barcode;

    @NotNull(message = "Product price cannot be null")
    @DecimalMin(value = "0.01", message = "Product price must be greater than 0")
    @Digits(integer = 10, fraction = 2, message = "Price must have at most 10 integer digits and 2 decimal places")
    private BigDecimal price;

    @NotNull(message = "Stock cannot be null")
    @Min(value = 0, message = "Stock cannot be negative")
    private int stock;

    @Min(value = 0, message = "Minimum stock cannot be negative")
    private int minStock;
}
