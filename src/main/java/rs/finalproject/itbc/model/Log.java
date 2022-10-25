package rs.finalproject.itbc.model;

import rs.finalproject.itbc.model.enums.LogType;

import java.time.LocalDateTime;

public class Log {
    private String message;
    private LogType logType;
    private LocalDateTime createdDate;
}
