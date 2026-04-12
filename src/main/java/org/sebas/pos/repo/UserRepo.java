package org.sebas.pos.repo;

import org.sebas.pos.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UserRepo extends JpaRepository<Users, UUID> {



}
