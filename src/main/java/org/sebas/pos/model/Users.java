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
    private String username;

    @Setter
    private String password;

    @Setter @Enumerated(EnumType.STRING)
    private ROLE role;

}