package org.chatapp.infrastructure.config;

import org.chatapp.core.contracts.persistence.UserRepository;
import org.chatapp.core.features.users.commands.CreateUserCommand;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModuleConfig {


    @Bean
    public CreateUserCommand createUserCommand(UserRepository repository){
        return new CreateUserCommand(repository);
    }



}
