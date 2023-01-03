package org.chatapp.infrastructure.controllers;


import jakarta.servlet.http.HttpServletRequest;
import org.chatapp.core.contracts.ICommandExecutor;
import org.chatapp.core.features.users.commands.DeleteUserCommand;
import org.chatapp.core.features.users.commands.RegisterUserCommand;
import org.chatapp.core.features.users.commands.UpdateUserCommand;
import org.chatapp.core.features.users.queries.GetUserCommand;
import org.chatapp.infrastructure.data.entities.*;
import org.chatapp.infrastructure.mappers.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;


//TODO: responses
@RestController
public class UserController {

    private ICommandExecutor commandExecutor;
    private CreateUserInputMapper createUserInputMapper;
    private UpdateUserInputMapper updateUserInputMapper;
    private RegisterUserCommand registerUserCommand;
    private GetUserCommand getUserCommand;
    private UpdateUserCommand updateUserCommand;
    private DeleteUserCommand deleteUserCommand;

    private AuthenticateUserCommand authenticateUserCommand;

    public UserController(ICommandExecutor commandExecutor, CreateUserInputMapper createUserInputMapper, UpdateUserInputMapper updateUserInputMapper, RegisterUserCommand registerUserCommand, GetUserCommand getUserCommand, UpdateUserCommand updateUserCommand, DeleteUserCommand deleteUserCommand, AuthenticateUserCommand authenticateUserCommand) {
        this.commandExecutor = commandExecutor;
        this.createUserInputMapper = createUserInputMapper;
        this.updateUserInputMapper = updateUserInputMapper;
        this.registerUserCommand = registerUserCommand;
        this.getUserCommand = getUserCommand;
        this.updateUserCommand = updateUserCommand;
        this.deleteUserCommand = deleteUserCommand;
        this.authenticateUserCommand = authenticateUserCommand;
    }


    //TODO: signin
    @PostMapping("/authenticate")
    public CompletableFuture<ResponseEntity<AuthenticationResponse>> loginUser(@RequestBody AuthenticationRequest authenticationRequest, HttpServletRequest httpServletRequest){

        return commandExecutor.execute(
                new AuthenticateUserCommand.InputBoundary(authenticationRequest),
                authenticateUserCommand,
                (outputValues) -> AuthenticationOutputMapper.map(outputValues.getToken(), httpServletRequest)
        );
    }




    //TODO: registration
    @PostMapping("/createUser")
    public CompletableFuture<ResponseEntity<ApiResponse>> registerUser(@RequestBody RegistrationRequest request, HttpServletRequest httpServletRequest){

        return commandExecutor.execute(
                createUserInputMapper.map(request),
                registerUserCommand,
                (outputValues) -> CreateUserOutputMapper.map(outputValues.getUser(), httpServletRequest)
        );
    }

    //TODO:
    @GetMapping("/getUser")
    public CompletableFuture<UserResponse> getUser(@RequestBody RetrieveUserRequest request){

        return commandExecutor.execute(
                new GetUserCommand.InputBoundary(request.getEmail()),
                getUserCommand,
                (outputValues) -> UserResponse.from(outputValues.getUser())
        );
    }


    @PostMapping("/updateUser/{id}")
    public CompletableFuture<ResponseEntity<ApiResponse>> updateUser(@RequestBody UserInfoUpdateRequest request, HttpServletRequest httpServletRequest){

        return commandExecutor.execute(
                updateUserInputMapper.map(request),
                updateUserCommand,
                (outputValues) -> UpdateUserOutputMapper.map(outputValues.getUser(), httpServletRequest)
        );
    }

    @PostMapping("/deleteUser/{id}")
    public CompletableFuture<ApiResponse> deleteUser(@PathVariable Long id){

        return commandExecutor.execute(
                new DeleteUserCommand.InputBoundary(id),
                deleteUserCommand,
                (outputValues) -> new ApiResponse(true, "User successfully deleted")
        );
    }
}
