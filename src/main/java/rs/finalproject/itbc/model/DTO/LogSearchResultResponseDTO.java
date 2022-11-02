package rs.finalproject.itbc.model.DTO;

import lombok.*;

import java.time.LocalDateTime;

@Value
public class LogSearchResultResponseDTO {
    String message;
    Integer logType;
    LocalDateTime createdDate;
}
