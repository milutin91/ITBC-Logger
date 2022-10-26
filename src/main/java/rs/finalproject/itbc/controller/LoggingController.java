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

@RestController
public class LoggingController {

    private final LogRepository logRepository;

    @Autowired
    public LoggingController(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    public ResponseEntity<?> newLog(Log log, @RequestHeader TokenRequestHeader token) {
        if(UserRepository.tokens.containsValue(token.getToken())){
            System.out.println("123123 " + token.getToken());
            logRepository.save(log);
            return ResponseEntity.status(HttpStatus.CREATED).body("Created");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("nesto");
    }
}
