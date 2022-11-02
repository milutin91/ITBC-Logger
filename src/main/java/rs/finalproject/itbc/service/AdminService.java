package rs.finalproject.itbc.service;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import rs.finalproject.itbc.controller.UserController;
import rs.finalproject.itbc.controller.log.TokenRequestHeader;
import rs.finalproject.itbc.model.enums.UserRole;

@Service
@AllArgsConstructor
public class AdminService {
    UserController userController;

    public ResponseEntity<?> getAllClients(TokenRequestHeader token) {
        return userController.getAllClients(token);
    }
}
