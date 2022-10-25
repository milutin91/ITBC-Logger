package rs.finalproject.itbc.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import rs.finalproject.itbc.controller.log.LogRequest;
import rs.finalproject.itbc.model.Log;
import rs.finalproject.itbc.model.enums.LogType;
import rs.finalproject.itbc.repository.LogRepository;

@Service
@AllArgsConstructor
public class LogService {

    private LogRequest logRequest;

    private LogRepository logRepository;

    @Autowired
    public Log logCreate(String message, LogType logType) {

        return new Log(logRequest.getMessage(), logRequest.getLogType());
    }
}
