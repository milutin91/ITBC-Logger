package rs.finalproject.itbc.service;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import rs.finalproject.itbc.controller.login.LoginRequest;


@Service
@AllArgsConstructor
public class LoginService {

    private UserService userService;

    public ResponseEntity<?> login(LoginRequest request) {
        return userService.loginUser(new LoginRequest(request.getAccount(),
                                                      request.getPassword()));
    }
}
