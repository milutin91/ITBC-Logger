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

        int logType = log.getLogType();
        if (logType > 2) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Incorrect logType");
        }

        if (log.getMessage().length() > 1024) {
            return ResponseEntity.status(HttpStatus.REQUEST_ENTITY_TOO_LARGE).body("Message should be less than 1024");
        }

        if (UserRepository.tokens.containsValue(token.getToken())) {
            logRepository.save(log);
            return ResponseEntity.status(HttpStatus.CREATED).body("Created");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Incorrect token");
        }
    }


    public ResponseEntity<?> searchLogs(LocalDateTime dateFrom,
                                        LocalDateTime dateTo,
                                        String message,
                                        Integer logType,
                                        @RequestHeader TokenRequestHeader token) {
        List<?> response;

        if (!userRepository.tokens.containsValue(token.getToken())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect token");
        }

        if (dateTo == null & dateFrom == null & Objects.equals(message, "") & logType == null) {

            response = logRepository.searchAllLogsByToken(token.getToken());
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }

        if (dateTo == null & Objects.equals(message, "") & logType == null) {

            response = logRepository.searchDateFrom(token.getToken(), dateFrom);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }

        if (dateFrom == null & Objects.equals(message, "") & logType == null) {

            response = logRepository.searchDateTo(token.getToken(), dateTo);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }

        if (dateFrom == null & dateTo == null & logType == null) {

            response = logRepository.searchMessage(token.getToken(), message);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }

        if (dateFrom == null & dateTo == null & Objects.equals(message, "")) {

            if (logType > 2 || logType < 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid logType");
            } else {
                response = logRepository.searchLogType(token.getToken(), logType);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }
        }

        if (Objects.equals(message, "") & logType == null) {

            if (dateFrom.isAfter(dateTo)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid dates");
            } else {
                response = logRepository.searchDateFromTo(token.getToken(), dateFrom, dateTo);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }
        }

        if (dateTo == null & logType == null) {

            response = logRepository.searchDateFromAndMessage(token.getToken(), dateFrom, message);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }

        if (dateTo == null & Objects.equals(message, "")) {

            if (logType > 2 || logType < 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid logType");
            } else {
                response = logRepository.searchDateFromAndLogtype(token.getToken(), dateFrom, logType);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }
        }

        if (dateFrom == null & logType == null) {

            response = logRepository.searchDateToAndMessage(token.getToken(), dateTo, message);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }

        if (dateFrom == null & Objects.equals(message, "")) {

            if (logType > 2 || logType < 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid logType");
            } else {
                response = logRepository.searchDateToAndLogtype(token.getToken(), dateTo, logType);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }
        }

        if (dateFrom == null & dateTo == null) {

            if (logType > 2 || logType < 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid logType");
            } else {
                response = logRepository.searchMessageAndLogtype(token.getToken(), message, logType);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }
        }

        if (logType == null) {

            if (dateFrom.isAfter(dateTo)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid dates");
            } else {
                response = logRepository.searchDateFromDateToAndMessage(token.getToken(), dateFrom, dateTo, message);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }
        }

        if (Objects.equals(message, "")) {

            if (logType > 2 || logType < 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid logType");
            } else if (dateFrom.isAfter(dateTo)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid dates");
            } else {
                response = logRepository.searchDateFromDateToAndLogtype(token.getToken(), dateFrom, dateTo, logType);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }
        }

        if (dateTo == null) {

            if (logType > 2 || logType < 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid logType");
            } else {
                response = logRepository.searchDateFromMessageAndLogtype(token.getToken(), dateFrom, message, logType);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }
        }

        if (dateFrom == null) {

            if (logType > 2 || logType < 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid logType");
            } else {
                response = logRepository.searchDateToMessageAndLogtype(token.getToken(), dateTo, message, logType);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }
        }
        if (logType > 2 || logType < 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid logType");
        } else if (dateFrom.isAfter(dateTo)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid dates");
        } else {
            response = logRepository.searchByAllParams(token.getToken(), dateFrom, dateTo, message, logType);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }
}
