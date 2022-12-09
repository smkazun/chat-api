package org.chatapp.domain.utils.entities;

import java.time.LocalDateTime;

public class UserBuilder {

    private long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private LocalDateTime dateCreated;

    public UserBuilder setUserId(long userId) {
        this.userId = userId;
        return this;
    }

    public UserBuilder setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserBuilder setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    public User build(){
        return new User(userId, firstName, lastName, email, password, dateCreated);
    }
}
