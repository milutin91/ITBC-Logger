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
import java.util.Objects;


@RestController
public class LoggingController {

    private final UserRepository userRepository;
    private final LogRepository logRepository;

    @Autowired
    public LoggingController(UserRepository userRepository, LogRepository logRepository) {
        this.userRepository = userRepository;
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


    public ResponseEntity<?> searchLogs(LocalDateTime dateFrom,
                                        LocalDateTime dateTo,
                                        String message,
                                        Integer logType,
                                        @RequestHeader TokenRequestHeader token) {
        List<?> tmp;

        if(dateTo == null & Objects.equals(message, "") & logType == null){

            tmp = logRepository.searchDateFrom(token.getToken(), dateFrom);
            System.out.println("1");
            return ResponseEntity.status(HttpStatus.OK).body(tmp);
        }

        if(dateFrom == null & Objects.equals(message, "") & logType == null){

            tmp = logRepository.searchDateTo(token.getToken(), dateTo);
            System.out.println("2");

            return ResponseEntity.status(HttpStatus.OK).body(tmp);
        }

        if(dateFrom == null & dateTo == null & logType == null){

            tmp = logRepository.searchMessage(token.getToken(), message);
            System.out.println("3");

            return ResponseEntity.status(HttpStatus.OK).body(tmp);
        }

        if(dateFrom == null & dateTo == null & Objects.equals(message, "")){

            tmp = logRepository.searchLogType(token.getToken(), logType);
            System.out.println("4");

            return ResponseEntity.status(HttpStatus.OK).body(tmp);
        }

        if(Objects.equals(message, "") & logType == null){

            tmp = logRepository.searchDateFromTo(token.getToken(), dateFrom, dateTo);
            System.out.println("5");

            return ResponseEntity.status(HttpStatus.OK).body(tmp);
        }

        if(dateTo == null & logType == null){

            tmp = logRepository.searchDateFromAndMessage(token.getToken(), dateFrom, message);
            System.out.println("6");

            return ResponseEntity.status(HttpStatus.OK).body(tmp);
        }

        if(dateTo == null & Objects.equals(message, "")){

            tmp = logRepository.searchDateFromAndLogtype(token.getToken(), dateFrom, logType);
            System.out.println("7");

            return ResponseEntity.status(HttpStatus.OK).body(tmp);
        }

        if(dateFrom == null & logType == null){

            tmp = logRepository.searchDateToAndMessage(token.getToken(), dateTo, message);
            System.out.println("8");

            return ResponseEntity.status(HttpStatus.OK).body(tmp);
        }

        if(dateFrom == null & Objects.equals(message, "")){

            tmp = logRepository.searchDateToAndLogtype(token.getToken(), dateTo, logType);
            System.out.println("9");

            return ResponseEntity.status(HttpStatus.OK).body(tmp);
        }

        if(dateFrom == null & dateTo == null){

            tmp = logRepository.searchMessageAndLogtype(token.getToken(), message, logType);
            System.out.println("10");

            return ResponseEntity.status(HttpStatus.OK).body(tmp);
        }

        if(logType == null){

            tmp = logRepository.searchDateFromDateToAndMessage(token.getToken(), dateFrom, dateTo, message);
            System.out.println("11");

            return ResponseEntity.status(HttpStatus.OK).body(tmp);
        }

        if(Objects.equals(message, "")){

            tmp = logRepository.searchDateFromDateToAndLogtype(token.getToken(), dateFrom, dateTo, logType);
            System.out.println("12");

            return ResponseEntity.status(HttpStatus.OK).body(tmp);
        }

        if(dateTo == null){

            tmp = logRepository.searchDateFromMessageAndLogtype(token.getToken(), dateFrom, message, logType);
            System.out.println("13");

            return ResponseEntity.status(HttpStatus.OK).body(tmp);
        }

        if(dateFrom == null){

            tmp = logRepository.searchDateToMessageAndLogtype(token.getToken(), dateTo, message, logType);
            System.out.println("14");

            return ResponseEntity.status(HttpStatus.OK).body(tmp);
        }

        tmp = logRepository.searchByAllParams(token.getToken(), dateFrom, dateTo, message, logType);
        System.out.println("15");

        return ResponseEntity.status(HttpStatus.OK).body(tmp);

    }

}
