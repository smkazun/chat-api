package org.chatapp.infrastructure.config;

import org.chatapp.core.contracts.persistence.UserRepository;
import org.chatapp.core.features.users.commands.RegisterUserCommand;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModuleConfig {


    @Bean
    public RegisterUserCommand createUserCommand(UserRepository repository){
        return new RegisterUserCommand(repository);
    }



}
