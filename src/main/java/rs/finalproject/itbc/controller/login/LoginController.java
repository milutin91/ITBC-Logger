package rs.finalproject.itbc.controller.login;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.finalproject.itbc.service.LoginService;

@RestController
@RequestMapping(path = "api/clients/login")
@AllArgsConstructor
public class LoginController {


    private LoginService loginService;

    @PostMapping
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest request){
        return loginService.login(request);
    }
}
