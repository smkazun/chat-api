package org.chatapp.infrastructure.controllers;


import jakarta.servlet.http.HttpServletRequest;
import org.chatapp.core.contracts.ICommandExecutor;
import org.chatapp.core.features.users.commands.DeleteUserCommand;
import org.chatapp.core.features.users.commands.RegisterUserCommand;
import org.chatapp.core.features.users.commands.UpdateUserCommand;
import org.chatapp.core.features.users.queries.GetUserCommand;
import org.chatapp.infrastructure.data.entities.ApiResponse;
import org.chatapp.infrastructure.data.entities.RegistrationRequest;
import org.chatapp.infrastructure.mappers.CreateUserInputMapper;
import org.chatapp.infrastructure.mappers.CreateUserOutputMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
public class UserController {

    private ICommandExecutor commandExecutor;
    private CreateUserInputMapper createUserInputMapper;

    private RegisterUserCommand registerUserCommand;
    private GetUserCommand getUserCommand;
    private UpdateUserCommand updateUserCommand;
    private DeleteUserCommand deleteUserCommand;

    public UserController(RegisterUserCommand createUserCommand, ICommandExecutor commandExecutor, CreateUserInputMapper createUserInputMapper){
        this.registerUserCommand = createUserCommand;
        this.commandExecutor = commandExecutor;
        this.createUserInputMapper = createUserInputMapper;
    }


    @PostMapping("/createUser")
    public CompletableFuture<ResponseEntity<ApiResponse>> registerUser(@RequestBody RegistrationRequest request, HttpServletRequest httpServletRequest){

        return commandExecutor.execute(
                createUserInputMapper.map(request),
                registerUserCommand,
                (outputValues) -> CreateUserOutputMapper.map(outputValues.getUser(), httpServletRequest)
        );
    }

    @GetMapping("/user")
    public CompletableFuture<ResponseEntity<ApiResponse>> getUser(@RequestBody RegistrationRequest request){

        return commandExecutor.execute(
                createUserInputMapper.map(request),
                getUserCommand,
                (outputValues) -> CreateUserOutputMapper.map(outputValues.getUser(), httpServletRequest)
        );
    }


    @PostMapping("/user/{id}")
    public CompletableFuture<ResponseEntity<ApiResponse>> updateUser(@RequestBody RegistrationRequest request, HttpServletRequest httpServletRequest){

        return commandExecutor.execute(
                createUserInputMapper.map(request.get),
                updateUserCommand,
                (outputValues) -> CreateUserOutputMapper.map(outputValues.getUser(), httpServletRequest)
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
