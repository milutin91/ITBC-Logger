package rs.finalproject.itbc.controller.admin;


import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.finalproject.itbc.controller.log.TokenRequestHeader;
import rs.finalproject.itbc.service.AdminService;

import java.util.UUID;


@RestController
@AllArgsConstructor
public class AdminController {

    private AdminService adminService;

    @GetMapping("api/clients")
    public ResponseEntity<?> getAllClients(@RequestHeader
    TokenRequestHeader Authorization) {
        return adminService.getAllClients(Authorization);
    }

    @PatchMapping("api/clients/{clientId}/reset-password")
    public ResponseEntity<?> changeClientPassword(@PathVariable(value = "clientId") String id, @RequestBody PasswordChangeRequest passwordChangeRequest,
                                                  @RequestHeader TokenRequestHeader Authorization) {
        return adminService.changeClientPassword(id, passwordChangeRequest, Authorization);
    }
}

