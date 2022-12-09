package org.chatapp.infrastructure.data.repositories;

import org.chatapp.infrastructure.data.entities.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

public interface JpaUserRepository extends JpaRepository<UserDTO, Long> {
    //boolean existsByEmail(String email);
    UserDTO findByEmail(String email);
}
