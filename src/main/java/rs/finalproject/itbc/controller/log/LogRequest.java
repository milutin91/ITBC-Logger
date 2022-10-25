package rs.finalproject.itbc.controller.log;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import rs.finalproject.itbc.model.enums.LogType;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class LogRequest {
    private String message;
    private LogType logType;
}
