package rs.finalproject.itbc.service;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import rs.finalproject.itbc.controller.register.RegistrationRequest;
import rs.finalproject.itbc.model.User;
import rs.finalproject.itbc.model.enums.UserRole;

@Service
@AllArgsConstructor
public class RegistrationService {

    private UserService userService;

    public ResponseEntity<String> register(RegistrationRequest request) {

        return userService.registerNewUser(new User(request.getUsername(),
                                                    request.getPassword(),
                                                    request.getEmail(),
                                                    UserRole.CLIENT));
    }
}
