package org.chatapp.core.contracts.persistence;

import org.chatapp.domain.utils.entities.User;
import java.util.List;
import java.util.Optional;

public interface UserRepository {


    public User addUser(User user);

    //public boolean existsByEmail(String email);
    public User findUserByEmail(String email);
    //public Optional<User> findUserById(long id);


}
