package org.chatapp.core.features.users.queries;

import org.chatapp.core.contracts.ICommand;
import org.chatapp.core.contracts.boundary.IInputBoundary;
import org.chatapp.core.contracts.boundary.IOutputBoundary;
import org.chatapp.core.contracts.persistence.UserRepository;
import org.chatapp.core.features.users.commands.RegisterUserCommand;
import org.chatapp.domain.entities.User;
import org.chatapp.domain.exceptions.AlreadyInUseException;
import org.chatapp.domain.exceptions.EmailNotFoundException;

/**
 * A user can view its own information
 */
public class GetUserCommand implements ICommand<GetUserCommand.InputBoundary, GetUserCommand.OutputBoundary> {
    private UserRepository repository;

    public GetUserCommand(UserRepository repository){
        this.repository = repository;
    }

    @Override
    public OutputBoundary execute(InputBoundary input) {
        User user = repository.findByEmail(input.getEmail())
                .orElseThrow(() -> new EmailNotFoundException(input.getEmail()));

        return new OutputBoundary(user);
    }


    //TODO: Lombok to make this cleaner
    /**
     * Incoming values for populating User, from external layers
     */
    public static class InputBoundary implements IInputBoundary {
        private final String email;

        public InputBoundary(String email) {
            this.email = email;
        }

        public String getEmail() {
            return email;
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
