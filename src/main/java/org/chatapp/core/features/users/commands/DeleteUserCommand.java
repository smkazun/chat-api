package org.chatapp.core.features.users.commands;

import org.chatapp.core.contracts.ICommand;
import org.chatapp.core.contracts.boundary.IInputBoundary;
import org.chatapp.core.contracts.boundary.IOutputBoundary;
import org.chatapp.core.contracts.persistence.UserRepository;
import org.chatapp.domain.entities.User;

/**
 * A user can delete itself so that no one can chat with them
 */
public class DeleteUserCommand implements ICommand<DeleteUserCommand.InputBoundary, DeleteUserCommand.OutputBoundary> {
    private UserRepository repository;

    public DeleteUserCommand(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public OutputBoundary execute(InputBoundary input) {

        repository.deleteById(input.getId());

        return new OutputBoundary(String.format("User %s has been deleted", input.getId()));
    }


    //TODO: Lombok to make this cleaner
    /**
     * Incoming values for populating User, from external layers
     */
    public static class InputBoundary implements IInputBoundary {
        private final Long id;

        public InputBoundary(Long id) {
            this.id = id;
        }

        public Long getId() {
            return id;
        }
    }

    /**
     * Outgoing values for external layers
     */
    public static class OutputBoundary implements IOutputBoundary {
        private final String message;

        public OutputBoundary(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}
