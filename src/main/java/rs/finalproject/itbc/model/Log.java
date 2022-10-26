package rs.finalproject.itbc.model;

import lombok.*;
import org.springframework.stereotype.Component;
import rs.finalproject.itbc.model.enums.LogType;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Component
@Table(name = "logs")
public class Log {
    @Id
    @Column(name = "logID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long logID;
    private String message;

    @Enumerated
    private LogType logType;
    private String token;
    private LocalDateTime createdDate = LocalDateTime.now();

    public Log(String message, LogType logType, String token) {
        this.message = message;
        this.logType = logType;
        this.token = token;
    }

}
