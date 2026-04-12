package org.sebas.pos.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import java.lang.Integer;


@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "SaleItems")
public class SaleItem {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;


    @Setter
    @ManyToOne(optional = false)
    @JoinColumn(name = "sale_id")
    @JsonBackReference
    private Sales sale;

    @Setter
    private Integer quantity;

}
