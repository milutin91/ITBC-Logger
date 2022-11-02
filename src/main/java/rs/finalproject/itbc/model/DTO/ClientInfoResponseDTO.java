package rs.finalproject.itbc.model.DTO;

import lombok.*;

import java.util.UUID;
@Value
//@Data
//@AllArgsConstructor
//@NoArgsConstructor@EqualsAndHashCode
public class ClientInfoResponseDTO {
    UUID userID;
    String username;
    String email;
}
