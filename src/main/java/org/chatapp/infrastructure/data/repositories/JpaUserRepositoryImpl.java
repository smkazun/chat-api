package org.chatapp.infrastructure.data.repositories;

import org.chatapp.core.contracts.persistence.UserRepository;
import org.chatapp.domain.utils.entities.User;
import org.chatapp.infrastructure.data.entities.UserDTO;
import org.springframework.stereotype.Repository;


@Repository
public class JpaUserRepositoryImpl implements UserRepository {
    JpaUserRepository repository;

    public JpaUserRepositoryImpl(JpaUserRepository repository){
        this.repository = repository;
    }

    @Override
    public User addUser(User user){
        final UserDTO data = UserDTO.from(user);
        return repository.save(data).fromThis();
    }


    @Override
    public User findUserByEmail(String email) {
        UserDTO result = repository.findByEmail(email);
        return result.fromThis();
    }

}
