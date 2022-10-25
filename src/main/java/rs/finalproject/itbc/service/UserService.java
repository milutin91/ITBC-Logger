package rs.finalproject.itbc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import rs.finalproject.itbc.controller.login.LoginRequest;
import rs.finalproject.itbc.model.User;
import rs.finalproject.itbc.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final Validator validator;

    @Autowired
    public UserService(UserRepository userRepository, Validator validator) {
        this.userRepository = userRepository;
        this.validator = validator;
    }

    public ResponseEntity<String> registerNewUser(User user) {
        if (!validator.emailValid(user.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("email must be valid");
        }

        if (!validator.usernameValid(user.getUsername())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("username at least 3 characters and no spaces");
        }

        if (!validator.passwordValid(user.getPassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("password at least 8 characters and one letter and one number");
        }

        if (userRepository.existsUserByUsername(user.getUsername())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("username alredy exist");
        } else if (userRepository.existsUserByEmail(user.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("email alredy exist");
        } else {
            userRepository.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("Registered");
        }
    }

    public ResponseEntity<?> loginUser(LoginRequest logRequest) {

        if (userRepository.findUserByUsernameAndPassword(logRequest.getUsername(), logRequest.getPassword()).isEmpty()) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email/Username or password incorrect");

        } else {
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }
    }


}
