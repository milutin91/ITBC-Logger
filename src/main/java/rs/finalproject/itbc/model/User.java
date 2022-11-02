package rs.finalproject.itbc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Type;
import org.springframework.stereotype.Component;

import rs.finalproject.itbc.model.enums.UserRole;

import javax.persistence.*;
import java.util.UUID;



@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Component
@Table(name = "users")
public class User {

    private String username;
    @Id
    @Column(name = "userID")
    @GeneratedValue
    @Type(type="org.hibernate.type.UUIDCharType")
    private UUID userID;
    @JsonIgnore
    private String password;
    private String email;
    @JsonIgnore
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    public User(String username,
                String password,
                String email,
                UserRole userRole) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.userRole = userRole;
    }

}
