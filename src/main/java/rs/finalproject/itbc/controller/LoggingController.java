package rs.finalproject.itbc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import rs.finalproject.itbc.controller.log.TokenRequestHeader;
import rs.finalproject.itbc.model.Log;
import rs.finalproject.itbc.repository.LogRepository;
import rs.finalproject.itbc.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.List;


@RestController
public class LoggingController {
    private final LogRepository logRepository;

    @Autowired
    public LoggingController(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    public ResponseEntity<?> newLog(Log log, @RequestHeader TokenRequestHeader token) {

        int temp = log.getLogType().ordinal();
        if(temp > 2) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Incorrect logType");
        }

        if(log.getMessage().length() > 1024){
            return ResponseEntity.status(HttpStatus.REQUEST_ENTITY_TOO_LARGE).body("Message should be less than 1024");
        }

        if(UserRepository.tokens.containsValue(token.getToken())){
            logRepository.save(log);
            return ResponseEntity.status(HttpStatus.CREATED).body("Created");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Incorrect token");
        }
    }


    public ResponseEntity<?> searchLogs(LocalDateTime dateFrom) {

            List<?> tmp = logRepository.dateFrom("625ec1ac-7ed1-2c47-b1eb-e8ce6cd1f2d8", dateFrom);

            return ResponseEntity.status(HttpStatus.OK).body(tmp);


    }
}
