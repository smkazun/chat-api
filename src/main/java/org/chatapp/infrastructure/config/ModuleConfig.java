package org.chatapp.infrastructure.config;

import org.chatapp.core.contracts.persistence.UserRepository;
import org.chatapp.core.features.users.commands.DeleteUserCommand;
import org.chatapp.core.features.users.commands.RegisterUserCommand;
import org.chatapp.core.features.users.commands.UpdateUserCommand;
import org.chatapp.core.features.users.queries.GetUserCommand;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModuleConfig {


    @Bean
    public RegisterUserCommand registerUserCommand(UserRepository repository){
        return new RegisterUserCommand(repository);
    }

    @Bean
    public GetUserCommand getUserCommand(UserRepository repository){
        return new GetUserCommand(repository);
    }

    @Bean
    public UpdateUserCommand updateUserCommand(UserRepository repository){
        return new UpdateUserCommand(repository);
    }

    @Bean
    public DeleteUserCommand deleteUserCommand(UserRepository repository){
        return new DeleteUserCommand(repository);
    }


}
