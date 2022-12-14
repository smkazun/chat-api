package org.chatapp.infrastructure.mappers;

import org.chatapp.core.features.users.commands.RegisterUserCommand;
import org.chatapp.infrastructure.data.entities.RegistrationRequest;
import org.springframework.stereotype.Service;

@Service
public class CreateUserInputMapper {

    public RegisterUserCommand.InputBoundary map(RegistrationRequest request){
        return new RegisterUserCommand.InputBoundary(
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getPassword()
        );
    }
}
