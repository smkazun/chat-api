package org.chatapp.infrastructure.data.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.chatapp.domain.entities.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;


@Entity(name="APP_USERS")
public class UserDTO {

    public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    @JsonIgnore
    private String password;
    @Column(nullable = false)
    private LocalDateTime dateCreated;

    @Column(nullable = true)
    private LocalDateTime dateUpdated;

    protected UserDTO() {
    }

    public UserDTO(long id, String firstName, String lastName, String email, String password, LocalDateTime dateCreated, LocalDateTime dateUpdated)
    {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.setPassword(password);
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
    }


    public void setPassword(String password) {
        this.password = PASSWORD_ENCODER.encode(password);
    }

    public static UserDTO from(User user) {
        return new UserDTO(user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                user.getDateCreated(),
                user.getDateUpdated()
        );
    }

    public User fromThis(){
        return new User(id,
                firstName,
                lastName,
                email,
                password,
                dateCreated,
                dateUpdated
        );
    }


}
