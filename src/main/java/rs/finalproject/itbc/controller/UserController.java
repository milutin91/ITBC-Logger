package rs.finalproject.itbc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import rs.finalproject.itbc.controller.log.TokenRequestHeader;
import rs.finalproject.itbc.controller.login.LoginRequest;
import rs.finalproject.itbc.model.DTO.ClientInfoResponseDTO;
import rs.finalproject.itbc.model.User;
import rs.finalproject.itbc.model.enums.UserRole;
import rs.finalproject.itbc.repository.UserRepository;
import rs.finalproject.itbc.service.Validator;

import java.util.*;

@RestController
public class UserController {

    private final UserRepository userRepository;
    private final Validator validator;

    @Autowired
    public UserController(UserRepository userRepository, Validator validator) {
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
        if (validator.emailValid(logRequest.getAccount())) {

            if (userRepository.findUserByEmailAndPassword(logRequest.getAccount(), logRequest.getPassword()).isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email/Username or password incorrect");
            } else {
                String userID = userRepository.findByEmail(logRequest.getAccount()).get(0).getUserID().toString();
                UserRole tmp = userRepository.findUserRoleByEmail(logRequest.getAccount());
                if(tmp.equals(UserRole.ADMIN)){
                    userRepository.adminTokens.put("token", userID);
                    System.out.println(userRepository.adminTokens);

                    return ResponseEntity.status(HttpStatus.OK).body(userRepository.adminTokens);
                }else {
                    userRepository.tokens.put("token", userID);

                    return ResponseEntity.status(HttpStatus.OK).body(userRepository.tokens);
                }
            }
        } else {

            if (userRepository.findUserByUsernameAndPassword(logRequest.getAccount(), logRequest.getPassword()).isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email/Username or password incorrect");
            } else {
                String userID = userRepository.findByUsername(logRequest.getAccount()).get(0).getUserID().toString();
                UserRole tmp = userRepository.findUserRoleByUsername(logRequest.getAccount());
                if(tmp.equals(UserRole.ADMIN)){
                    userRepository.adminTokens.put("token", userID);
                    System.out.println(userRepository.adminTokens);

                    return ResponseEntity.status(HttpStatus.OK).body(userRepository.adminTokens);
                }else {
                    userRepository.tokens.put("token", userID);
                    return ResponseEntity.status(HttpStatus.OK).body(userRepository.tokens);
                }
            }
        }
    }



    public ResponseEntity<?> getAllClients(TokenRequestHeader token) {
        if(userRepository.tokens.containsValue(token.getToken())) {
            List<ClientInfoResponseDTO> users = userRepository.findAllClients(UserRole.ADMIN);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Correct token, but not admin");
        }
        if(userRepository.adminTokens.containsValue(token.getToken())) {
            List<ClientInfoResponseDTO> users = userRepository.findAllClients(UserRole.ADMIN);
            return ResponseEntity.status(HttpStatus.OK).body(users);
        }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Incorrect token");
    }
}
