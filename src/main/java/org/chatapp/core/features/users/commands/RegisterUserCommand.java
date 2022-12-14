package org.chatapp.core.features.users.commands;

import org.chatapp.core.contracts.ICommand;
import org.chatapp.core.contracts.boundary.IInputBoundary;
import org.chatapp.core.contracts.boundary.IOutputBoundary;
import org.chatapp.core.contracts.persistence.UserRepository;
import org.chatapp.domain.exceptions.AlreadyInUseException;
import org.chatapp.domain.entities.User;
import org.chatapp.domain.entities.UserBuilder;

import java.time.LocalDateTime;

/**
 * A user can register itself to chat with others
 */
public class RegisterUserCommand implements ICommand<RegisterUserCommand.InputBoundary, RegisterUserCommand.OutputBoundary> {
    private UserRepository repository;

    public RegisterUserCommand(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public OutputBoundary execute(InputBoundary input) {

        throwExceptionIfExistsByEmail(input.getEmail());

        User user = createUser(input);

        return new OutputBoundary(repository.save(user));
    }


    private User createUser(InputBoundary input){
        return new UserBuilder()
                .setFirstName(input.getFirstName())
                .setLastName(input.getLastName())
                .setEmail(input.getEmail())
                .setPassword(input.getPassword())
                .setDateCreated(LocalDateTime.now())
                //dateUpdate left blank
                .build();
    }

    private void throwExceptionIfExistsByEmail(String email){
        if(repository.existsByEmail(email)){
            throw new AlreadyInUseException("Email %s is already registered", email); //TODO: move string to constant file
        }
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