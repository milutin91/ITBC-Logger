package rs.finalproject.itbc.service;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;
import rs.finalproject.itbc.controller.LoggingController;
import rs.finalproject.itbc.controller.log.LogRequest;
import rs.finalproject.itbc.controller.log.TokenRequestHeader;
import rs.finalproject.itbc.model.Log;
import rs.finalproject.itbc.model.enums.LogType;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class LogService {

    private LoggingController loggingController;

    public ResponseEntity<?> logCreate(LogRequest request, TokenRequestHeader requestHeader){
        return loggingController.newLog(new Log(request.getMessage(), request.getLogType().ordinal(), requestHeader.getToken()), requestHeader);
    }

    public ResponseEntity<?> searchLogs(LocalDateTime dateFrom, LocalDateTime dateTo, String message, Integer logType, TokenRequestHeader token) {
        return loggingController.searchLogs(dateFrom, dateTo, message, logType, token);
    }


}
