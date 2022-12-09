package org.chatapp.core.features.users.commands;

import org.chatapp.core.contracts.ICommand;
import org.chatapp.core.contracts.boundary.IInputBoundary;
import org.chatapp.core.contracts.boundary.IOutputBoundary;
import org.chatapp.core.contracts.persistence.UserRepository;
import org.chatapp.domain.utils.UserIdGenerator;
import org.chatapp.domain.utils.entities.User;
import org.chatapp.domain.utils.entities.UserBuilder;

import java.time.LocalDateTime;

public class CreateUserCommand implements ICommand<CreateUserCommand.InputBoundary, CreateUserCommand.OutputBoundary> {
    private UserRepository repository;

    public CreateUserCommand(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public OutputBoundary execute(InputBoundary input) {

        //if(repository.existsByEmail(input.getEmail())){
            //throw new RuntimeException();
        //}

        User user = new UserBuilder()
                .setUserId(UserIdGenerator.createUniqueId())
                .setFirstName(input.getFirstName())
                .setLastName(input.getLastName())
                .setEmail(input.getEmail())
                .setPassword(input.getPassword())
                .setDateCreated(LocalDateTime.now())
                .build();

        return new OutputBoundary(repository.addUser(user));
    }




    //TODO: Lombok to make this cleaner
    /**
     * Incoming values for populating User, from external layers
     */
    public static class InputBoundary implements IInputBoundary {
        private final String firstName;
        private final String lastName;
        private final String email;
        private final String password;

        public InputBoundary(String firstName, String lastName, String email, String password) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.password = password;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public String getEmail() {
            return email;
        }

        public String getPassword() {
            return password;
        }

    }

    /**
     * Outgoing values for external layers
     */
    public static class OutputBoundary implements IOutputBoundary {
        private final User user;

        public OutputBoundary(User user) {
            this.user = user;
        }

        public User getUser() {
            return user;
        }
    }

}