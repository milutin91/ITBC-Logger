package rs.finalproject.itbc.controller.log;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.finalproject.itbc.service.LogService;

import java.time.LocalDateTime;

@RestController
//@RequestMapping(path = "api/logs/create")
@AllArgsConstructor

public class LogController {

    private LogService logService;

    @PostMapping("api/logs/create")
    public ResponseEntity<?> logCreate(@RequestBody LogRequest request, @RequestHeader TokenRequestHeader token) {

        return logService.logCreate(request, token);
    }

    @GetMapping("api/logs/search")
    public ResponseEntity<?> searchLogs(@RequestParam LocalDateTime dateFrom){
        return logService.searchLogs(dateFrom);
    }
}

