package org.sebas.pos.repo;

import org.sebas.pos.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepo extends JpaRepository<Users, UUID> {
    Users getUsersByUsername(String username);
}
