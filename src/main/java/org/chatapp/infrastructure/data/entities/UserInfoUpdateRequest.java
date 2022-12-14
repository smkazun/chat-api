package org.chatapp.infrastructure.data.entities;

import org.chatapp.core.features.users.commands.UpdateUserCommand;

public class UserInfoUpdateRequest {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public UserInfoUpdateRequest(Long id, String firstName, String lastName, String email, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public static UpdateUserCommand.InputBoundary from(UserInfoUpdateRequest request){
        return new UpdateUserCommand.InputBoundary(
                request.getId(),
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getPassword()
        );
    }

    public Long getId(){
        return id;
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
