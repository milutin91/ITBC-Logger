package rs.finalproject.itbc.controller.login;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class LoginRequest {

    private final String account;
    private final String password;

}
