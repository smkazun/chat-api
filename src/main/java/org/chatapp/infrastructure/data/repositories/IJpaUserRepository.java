package org.chatapp.infrastructure.data.repositories;

import org.chatapp.infrastructure.data.entities.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IJpaUserRepository extends JpaRepository<UserDTO, Long> {
    //boolean existsByEmail(String email);
    UserDTO findByEmail(String email);
}
