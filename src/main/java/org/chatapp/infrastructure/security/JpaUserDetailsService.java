package org.chatapp.infrastructure.security;


import org.chatapp.infrastructure.data.entities.UserDTO;
import org.chatapp.infrastructure.data.repositories.IJpaUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class JpaUserDetailsService implements UserDetailsService {

    private final IJpaUserRepository repository;

    @Autowired
    public JpaUserDetailsService(IJpaUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserDTO> load = repository.findByEmail(username);

        if(!load.isPresent()){
            throw new UsernameNotFoundException(load.toString());
        }

        org.chatapp.domain.entities.User user = load.get().fromThis(); //TODO:

        return new User(user.getFullName(), user.getPassword(), AuthorityUtils.NO_AUTHORITIES);
    }
}
