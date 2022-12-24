package org.chatapp.core.features.users.queries;

import org.chatapp.core.contracts.ICommand;
import org.chatapp.core.contracts.boundary.IInputBoundary;
import org.chatapp.core.contracts.boundary.IOutputBoundary;
import org.chatapp.core.contracts.persistence.UserRepository;
import org.chatapp.domain.entities.User;
import org.chatapp.domain.exceptions.EmailNotFoundException;

public class AuthenticateUserCommand implements ICommand<AuthenticateUserCommand.InputBoundary, AuthenticateUserCommand.OutputBoundary> {
    private UserRepository repository;

    public AuthenticateUserCommand(UserRepository repository) {
        this.repository = repository;
    }



    @Override
    public OutputBoundary execute(InputBoundary input) {
        User user = repository.findByEmail(input.getEmail())
                .orElseThrow(() -> new EmailNotFoundException(input.getEmail()));

        return new AuthenticateUserCommand.OutputBoundary(user);
    }


    public static class InputBoundary implements IInputBoundary{
        private final String email;
        private final String password;

        public InputBoundary(String email, String password) {
            this.email = email;
            this.password = password;
        }

        public String getEmail() {
            return email;
        }

        public String getPassword() {
            return password;
        }
    }

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
