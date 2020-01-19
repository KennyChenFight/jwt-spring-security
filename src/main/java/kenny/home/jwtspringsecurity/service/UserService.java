package kenny.home.jwtspringsecurity.service;

import kenny.home.jwtspringsecurity.model.Register;
import kenny.home.jwtspringsecurity.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface UserService {

    public ResponseEntity<?> createUser(Register register);

    public ResponseEntity<?> getAllUser();

    public ResponseEntity<?> getOneUser(String id);
}
