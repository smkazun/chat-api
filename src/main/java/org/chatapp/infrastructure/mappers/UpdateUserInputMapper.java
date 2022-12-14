package org.chatapp.infrastructure.mappers;

import org.chatapp.core.features.users.commands.UpdateUserCommand;
import org.chatapp.infrastructure.data.entities.UserInfoUpdateRequest;
import org.springframework.stereotype.Service;

@Service
public class UpdateUserInputMapper {

    public UpdateUserCommand.InputBoundary map(UserInfoUpdateRequest request){
        return new UpdateUserCommand.InputBoundary(
                request.getId(),
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getPassword()
        );
    }
}
