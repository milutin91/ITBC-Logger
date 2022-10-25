package rs.finalproject.itbc.service;

import org.springframework.stereotype.Service;
import java.util.regex.Pattern;

@Service
public class Validator {
    public boolean emailValid(String email) {
        String regex = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

        return Pattern.compile(regex).matcher(email).matches();
    }

    public boolean usernameValid(String username) {
        String regex = "[^\s]{3,}";

        return Pattern.compile(regex).matcher(username).matches();
    }

    public boolean passwordValid(String password) {
        String regex = "^(?=.*[0-9])(?=.*[a-z]).{8,}$";

        return Pattern.compile(regex).matcher(password).matches();
    }
}
