package org.chatapp.infrastructure.mappers;

import org.chatapp.core.features.users.commands.CreateUserCommand;
import org.chatapp.infrastructure.data.entities.RegistrationRequest;
import org.springframework.stereotype.Service;

@Service
public class CreateUserInputMapper {

    public CreateUserCommand.InputBoundary map(RegistrationRequest request){
        return new CreateUserCommand.InputBoundary(
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getPassword()
        );
    }
}
