package org.chatapp.infrastructure.mappers;

import jakarta.servlet.http.HttpServletRequest;
import org.chatapp.core.features.users.commands.DeleteUserCommand;
import org.chatapp.domain.entities.User;
import org.chatapp.infrastructure.data.entities.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public final class UpdateUserOutputMapper {

    public static ResponseEntity<ApiResponse> map(User user, HttpServletRequest request){
        URI location = ServletUriComponentsBuilder
                .fromContextPath(request)
                .path("/UpdateUser/{id}")
                .buildAndExpand(user.getId())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(new ApiResponse(true, String.format("User %d has been updated successfully", user.getId())));
    }
}
