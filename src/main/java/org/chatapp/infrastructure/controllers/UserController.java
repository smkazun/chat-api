package org.chatapp.infrastructure.controllers;


import jakarta.servlet.http.HttpServletRequest;
import org.chatapp.core.contracts.ICommandExecutor;
import org.chatapp.core.features.users.commands.CreateUserCommand;
import org.chatapp.infrastructure.data.entities.ApiResponse;
import org.chatapp.infrastructure.data.entities.RegistrationRequest;
import org.chatapp.infrastructure.mappers.CreateUserInputMapper;
import org.chatapp.infrastructure.mappers.CreateUserOutputMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.concurrent.CompletableFuture;

@RestController
public class UserController {

    private CreateUserCommand createUserCommand;
    private ICommandExecutor commandExecutor;
    private CreateUserInputMapper createUserInputMapper;

    public UserController(CreateUserCommand createUserCommand, ICommandExecutor commandExecutor, CreateUserInputMapper createUserInputMapper){
        this.createUserCommand = createUserCommand;
        this.commandExecutor = commandExecutor;
        this.createUserInputMapper = createUserInputMapper;
    }


    //THIS WORKSS!!!!!
    @GetMapping("/user")
    public String getUserString(){
        return "user";
    }


    @PostMapping("/CreateUser")
    public CompletableFuture<ResponseEntity<ApiResponse>> registerUser(@RequestBody RegistrationRequest request, HttpServletRequest httpServletRequest){

        return commandExecutor.execute(
                createUserInputMapper.map(request),
                createUserCommand,
                (outputValues) -> CreateUserOutputMapper.map(outputValues.getUser(), httpServletRequest)
        );
    }
}
