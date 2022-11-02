package rs.finalproject.itbc.model.DTO;

import lombok.*;

import java.util.UUID;
@Value
public class ClientInfoResponseDTO {
    UUID userID;
    String username;
    String email;
    Long logCount;
}
