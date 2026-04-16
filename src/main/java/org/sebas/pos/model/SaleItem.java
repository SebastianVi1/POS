package org.sebas.pos.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import java.lang.Integer;


@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SaleItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Setter
    @ManyToOne(optional = false)
    @JoinColumn(name = "sale_id", nullable = false)
    @JsonBackReference
    private Sales sale;

    @Setter
    @Column(nullable = false)
    private Integer quantity;

}
