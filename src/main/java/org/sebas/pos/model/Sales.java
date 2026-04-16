package org.sebas.pos.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
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
    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @Setter
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PAYMENT payment_type;

    @Setter
    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<SaleItem> productsList = new ArrayList<>();

    @Setter
    @Column(nullable = false)
    private BigDecimal totalPrice;

    @Setter
    @Column(nullable = false)
    private Integer totalProducts;

    @Setter
    private Integer ivaPercentage = 16;

}
