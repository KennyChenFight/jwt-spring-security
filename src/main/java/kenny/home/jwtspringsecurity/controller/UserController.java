package kenny.home.jwtspringsecurity.controller;

import kenny.home.jwtspringsecurity.model.Register;
import kenny.home.jwtspringsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("v1/users")
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public ResponseEntity<?> getAllUser() {
        return userService.getAllUser();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getOneUser(@PathVariable String id) {
        String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        if (!id.equals(userId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("error", "get another user account is forbidden"));
        }
        return userService.getOneUser(id);
    }

    @PostMapping(value = "/")
    public ResponseEntity<?> createUser(@RequestBody Register register) {
        return userService.createUser(register);
    }
}
