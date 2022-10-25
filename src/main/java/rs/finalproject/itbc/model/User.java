package rs.finalproject.itbc.model;

import lombok.*;
import org.springframework.stereotype.Component;
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

    private UUID userID;
    private String password;
    private String email;
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

//    public User(String username, String password) {
//        this.username = username;
//        this.password = password;
//    }
}
