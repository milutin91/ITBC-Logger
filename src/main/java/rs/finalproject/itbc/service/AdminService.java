package rs.finalproject.itbc.service;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import rs.finalproject.itbc.controller.UserController;
import rs.finalproject.itbc.controller.admin.PasswordChangeRequest;
import rs.finalproject.itbc.controller.log.TokenRequestHeader;
import rs.finalproject.itbc.model.enums.UserRole;

import java.util.UUID;

@Service
@AllArgsConstructor
public class AdminService {
    UserController userController;

    public ResponseEntity<?> getAllClients(TokenRequestHeader token) {
        return userController.getAllClients(token);
    }

    public ResponseEntity<?> changeClientPassword(String id, PasswordChangeRequest passwordChangeRequest, TokenRequestHeader token) {
        return userController.changeClientPassword(id, passwordChangeRequest, token);
    }
}
