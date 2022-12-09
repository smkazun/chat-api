package org.chatapp.infrastructure.data.entities;


import org.chatapp.domain.utils.entities.User;

public class UserResponse {
    private final String firstName;
    private final String lastName;
    private final String email;

    public UserResponse(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public static UserResponse from(User user){
        return new UserResponse(
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()
        );
    }
}
