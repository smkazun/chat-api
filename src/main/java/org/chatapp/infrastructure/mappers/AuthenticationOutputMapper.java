package org.chatapp.infrastructure.mappers;

import jakarta.servlet.http.HttpServletRequest;
import org.chatapp.domain.entities.User;
import org.chatapp.infrastructure.data.entities.ApiResponse;
import org.chatapp.infrastructure.data.entities.AuthenticationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

//TODO: why final
public final class AuthenticationOutputMapper {

    public static ResponseEntity<AuthenticationResponse> map(String token, HttpServletRequest request){

        URI location = ServletUriComponentsBuilder
                .fromContextPath(request)
                .path("/authenticate")
                .buildAndExpand(token)
                .toUri();

        return ResponseEntity.ok(new AuthenticationResponse(token));
    }
}
