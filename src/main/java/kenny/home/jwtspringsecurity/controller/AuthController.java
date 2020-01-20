package kenny.home.jwtspringsecurity.controller;

import kenny.home.jwtspringsecurity.model.AuthRequest;
import kenny.home.jwtspringsecurity.service.UserService;
import kenny.home.jwtspringsecurity.service.impl.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = "/")
    public ResponseEntity<?> getToken(@RequestBody AuthRequest authRequest) {
        return authService.getToken(authRequest);
    }
}
