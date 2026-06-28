package org.sebas.pos.features.auth.repo;

import org.sebas.pos.features.auth.domain.RefreshToken;
import org.sebas.pos.features.user.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RefreshTokenRepo extends JpaRepository<RefreshToken, Long> {
    RefreshToken findByToken(String token);

    @Query("UPDATE RefreshToken rt SET rt.revoked = true WHERE rt.user = :user")
    void revokeAllByUser(Users user);
}
