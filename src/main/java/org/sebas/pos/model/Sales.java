package org.sebas.pos.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "Sales")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Sales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private Instant createdAt;

    @Setter
    private PAYMENT payment_type;

    @Setter
    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<SaleItem> productsList = new ArrayList<>();


    @Setter
    @NotNull(message = "Total price cannot be null")
    private BigDecimal totalPrice;

    @Setter
    @NotNull(message = "Total products cannot be null")
    private Integer totalProducts;

    @Setter
    private Integer ivaPercentage = 16;

}
