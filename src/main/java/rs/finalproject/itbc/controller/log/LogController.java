package rs.finalproject.itbc.controller.log;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.finalproject.itbc.repository.UserRepository;
import rs.finalproject.itbc.service.LogService;

@RestController
@RequestMapping(path = "api/logs/create")
@AllArgsConstructor

public class LogController {

    private LogService logService;

    @PostMapping
    public ResponseEntity<?> logCreate(@RequestBody LogRequest request, @RequestHeader TokenRequestHeader token) {

        return logService.logCreate(request, token);
    }
}

