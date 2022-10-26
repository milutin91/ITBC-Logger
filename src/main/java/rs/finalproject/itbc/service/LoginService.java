package rs.finalproject.itbc.service;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import rs.finalproject.itbc.controller.UserController;
import rs.finalproject.itbc.controller.login.LoginRequest;


@Service
@AllArgsConstructor
public class LoginService {

    private UserController userController;

    public ResponseEntity<?> login(LoginRequest request) {
        return userController.loginUser(new LoginRequest(request.getAccount(),
                                                      request.getPassword()));
    }
}
