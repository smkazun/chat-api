package org.chatapp.infrastructure.data.repositories;

import org.chatapp.core.contracts.persistence.UserRepository;
import org.chatapp.domain.entities.User;
import org.chatapp.infrastructure.data.entities.UserDTO;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public class JpaUserRepositoryImpl implements UserRepository {
    IJpaUserRepository repository;

    public JpaUserRepositoryImpl(IJpaUserRepository repository){
        this.repository = repository;
    }

    @Override
    public User save(User user){
        final UserDTO result = UserDTO.from(user);
        return repository.save(result).fromThis();
    }

    @Override
    public Optional<User> findById(Long id) {
        final UserDTO result = repository.findById(id).get();
        return Optional.ofNullable(result.fromThis());
    }


    @Override
    public Optional<User> findByEmail(String email) {
        final UserDTO result = repository.findByEmail(email);
        return Optional.ofNullable(result.fromThis());
    }

    @Override
    public boolean existsByEmail(String email) {
       return repository.findByEmail(email) != null;
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

}
