package org.sebas.pos.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.time.LocalDateTime;


@Entity
@Table(name = "Sales")
public class Sales {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @CreationTimestamp
    public Instant createdAt;

    public PAYMENT payment_type;



}
