package org.sebas.pos.repo;

import org.sebas.pos.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepo extends JpaRepository<Users, UUID> {
    Optional<Users> getUsersByUsername(String username);
}
