package org.sebas.pos.features.user.repo;

import org.sebas.pos.features.user.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepo extends JpaRepository<Users, UUID> {
    Optional<Users> getUsersByUsername(String username);
}
