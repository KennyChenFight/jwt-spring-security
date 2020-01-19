package kenny.home.jwtspringsecurity.service.impl;

import kenny.home.jwtspringsecurity.model.Register;
import kenny.home.jwtspringsecurity.model.User;
import kenny.home.jwtspringsecurity.repository.UserRepository;
import kenny.home.jwtspringsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<?> createUser(Register register) {
        UUID id = UUID.randomUUID();
        String password = register.getPassword();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String passwordDigest = passwordEncoder.encode(password);

        User user = new User(id, register.getEmail(), passwordDigest, register.getName());
        userRepository.save(user);

        return ResponseEntity.ok(Map.of("userId", user.getId().toString()));
    }

    @Override
    public ResponseEntity<?> getAllUser() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @Override
    public ResponseEntity<?> getOneUser(String id) {
        UUID uuid = UUID.fromString(id);
        Optional<User> instance = userRepository.findById(uuid);
        if (instance.isPresent()) {
            return ResponseEntity.ok(instance.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "not found record"));
    }
}
