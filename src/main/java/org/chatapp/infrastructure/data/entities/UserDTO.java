package org.chatapp.infrastructure.data.entities;

import jakarta.persistence.*;
import org.chatapp.domain.utils.entities.User;

import java.time.LocalDateTime;


@Table(name="APP_USERS")
@Entity
public class UserDTO {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private LocalDateTime dateCreated;

    public UserDTO() {
    }

    public UserDTO(long id, String firstName, String lastName, String email, String password, LocalDateTime dateCreated)
    {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.dateCreated = dateCreated;
    }

    public static UserDTO from(User user) {
        return new UserDTO(user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                user.getDateCreated()
        );
    }

    public User fromThis(){
        return new User(id,
                firstName,
                lastName,
                email,
                password,
                dateCreated
        );
    }

}
