package org.chatapp.core.contracts.persistence;

import org.chatapp.domain.entities.User;

public interface UserRepository {


    public User addUser(User user);

    //public boolean existsByEmail(String email);
    public User findUserByEmail(String email);

    boolean existsByEmail(String email);
    //public Optional<User> findUserById(long id);


}
