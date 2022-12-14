package org.chatapp.domain.entities;

import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;



//TODO: rethink, can user be loggedOn, inActive, etc. delete itself,
public class User {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;

    public User(long id, String firstName, String lastName, String email, String password, LocalDateTime dateCreated, LocalDateTime dateUpdated) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
    }

    public String getFullName() {
        return firstName.concat(" ").concat(lastName);
    }

    public boolean passwordIsValid() {
        return password != null && password.length() > 8;
    }

    public long getId() {
        return id;
    }

    public User setId(long id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public User setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    public LocalDateTime getDateUpdated() {
        return dateUpdated;
    }

    public User setDateUpdated(LocalDateTime dateUpdated) {
        this.dateUpdated = dateUpdated;
        return this;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", dateCreated=" + dateCreated + '\'' +
                ", dateUpdated=" + dateUpdated +
                '}';
    }
}
