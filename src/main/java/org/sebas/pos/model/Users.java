package org.sebas.pos.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Builder
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Setter
    @Column(unique = true, nullable = false)
    private String username;

    @Setter
    @Column(nullable = false)
    private String password;

    @Setter
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ROLE role;

}