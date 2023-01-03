package org.chatapp.infrastructure.controllers;

import org.chatapp.core.contracts.ICommand;
import org.chatapp.core.contracts.boundary.IInputBoundary;
import org.chatapp.core.contracts.boundary.IOutputBoundary;
import org.chatapp.infrastructure.data.entities.AuthenticationRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;
import java.util.Collections;

public class AuthenticateUserCommand implements ICommand<AuthenticateUserCommand.InputBoundary, AuthenticateUserCommand.OutputBoundary> {
    //private UserRepository repository;
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;

    private static final Logger logger = LoggerFactory.getLogger(AuthenticateUserCommand.class);


    public AuthenticateUserCommand(TokenService tokenService, AuthenticationManager authenticationManager) {
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
    }



    @Override
    public OutputBoundary execute(InputBoundary input) {

        AuthenticationRequest req = input.getAuthenticationRequest();
        //Collection<? extends GrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority("USER"));

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword()));
        //logger.warn("attempting to set SecurityContext");
        //SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = tokenService.generateToken(authentication);

        return new AuthenticateUserCommand.OutputBoundary(token);
    }


    public static class InputBoundary implements IInputBoundary{
        private final AuthenticationRequest request;

        public InputBoundary(AuthenticationRequest request) {
            this.request = request;
        }

        public AuthenticationRequest getAuthenticationRequest() {
            return request;
        }
    }

    public static class OutputBoundary implements IOutputBoundary {
        private final String token;

        public OutputBoundary(String token) {
            this.token = token;
        }

        public String getToken() {
            return token;
        }
    }

}
