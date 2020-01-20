package kenny.home.jwtspringsecurity.service.impl;

import kenny.home.jwtspringsecurity.lib.JWTUtil;
import kenny.home.jwtspringsecurity.model.AuthRequest;
import kenny.home.jwtspringsecurity.model.User;
import kenny.home.jwtspringsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTUtil jwtUtil;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> instance = userRepository.findByEmail(email);
        if (instance.isPresent()) {
            return instance.get();
        }
        throw new UsernameNotFoundException("email not found");
    }

    public ResponseEntity<?> getToken(AuthRequest authRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
            User user = userRepository.findByEmail(authRequest.getEmail()).get();
            String token = jwtUtil.Sign(user.getId().toString());
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", token);
            return ResponseEntity.ok().headers(headers).body(null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }
}
