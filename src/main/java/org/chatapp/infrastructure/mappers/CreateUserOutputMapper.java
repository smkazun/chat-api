package org.chatapp.infrastructure.mappers;

import jakarta.servlet.http.HttpServletRequest;
import org.chatapp.domain.entities.User;
import org.chatapp.infrastructure.data.entities.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;

//TODO: why final
public final class CreateUserOutputMapper {

    public static ResponseEntity<ApiResponse> map(User user, HttpServletRequest request){

        URI location = ServletUriComponentsBuilder
                .fromContextPath(request)
                .path("/CreateUser/{id}")
                .buildAndExpand(user.getId())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(new ApiResponse(true, "User has been registered: " + user.toString()));
    }
}
