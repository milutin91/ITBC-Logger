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
    @GeneratedValue
    private Long logID;
    private String message;
    @Enumerated
    private LogType logType;
    private LocalDateTime createdDate = LocalDateTime.now();

    public Log(String message, LogType logType) {
        this.message = message;
        this.logType = logType;
    }

    public Log(String message, LogType logType, LocalDateTime createdDate) {
        this.message = message;
        this.logType = logType;
        this.createdDate = createdDate;
    }
}
