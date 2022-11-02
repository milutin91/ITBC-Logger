package rs.finalproject.itbc.controller.admin;


import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.finalproject.itbc.controller.log.TokenRequestHeader;
import rs.finalproject.itbc.service.AdminService;


@RestController
@RequestMapping(path = "api/clients")
@AllArgsConstructor
public class AdminController {

    private AdminService adminService;

    @GetMapping
    public ResponseEntity<?> getAllClients(@RequestHeader
    TokenRequestHeader Authorization) {
        return adminService.getAllClients(Authorization);
    }
}
