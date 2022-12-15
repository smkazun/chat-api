package org.chatapp.infrastructure.data.repositories;

import org.chatapp.infrastructure.data.entities.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IJpaUserRepository extends JpaRepository<UserDTO, Long> {
    Optional<UserDTO> findByEmail(String email);
    boolean existsByEmail(String email);
}
