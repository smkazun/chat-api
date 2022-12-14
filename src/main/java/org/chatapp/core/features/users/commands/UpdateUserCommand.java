package org.chatapp.core.features.users.commands;

import org.chatapp.core.contracts.ICommand;
import org.chatapp.core.contracts.boundary.IInputBoundary;
import org.chatapp.core.contracts.boundary.IOutputBoundary;
import org.chatapp.core.contracts.persistence.UserRepository;
import org.chatapp.domain.entities.User;
import org.chatapp.domain.exceptions.NotFoundException;
import org.chatapp.domain.utils.UserIdGenerator;

import java.time.LocalDateTime;

/**
 * A user can update its information, changing the way others see and interact with them
 */
public class UpdateUserCommand implements ICommand<UpdateUserCommand.InputBoundary, UpdateUserCommand.OutputBoundary> {
    private UserRepository repository;

    public UpdateUserCommand(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public OutputBoundary execute(InputBoundary input) {

        User user = repository.findById(input.getId())
                .orElseThrow(() -> new NotFoundException("User %s could not be found", input.getId()));

        updateUser(user, input);
        repository.save(user);

        return new OutputBoundary(user);
    }

    private void updateUser(User user, InputBoundary input){
        user.setFirstName(input.getFirstName())
                .setLastName(input.getLastName())
                .setEmail(input.getEmail())
                .setPassword(input.getPassword())
                .setDateUpdated(LocalDateTime.now());
    }


    //TODO: Lombok to make this cleaner
    /**
     * Incoming values for populating User, from external layers
     */
    public static class InputBoundary implements IInputBoundary {
        private final Long id;
        private final String firstName;
        private final String lastName;
        private final String email;
        private final String password;

        public InputBoundary(Long id, String firstName, String lastName, String email, String password) {
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.password = password;
        }

        public Long getId(){return  id;}

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
