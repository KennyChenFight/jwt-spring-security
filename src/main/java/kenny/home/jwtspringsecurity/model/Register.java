package kenny.home.jwtspringsecurity.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Register {
    private String email;
    private String name;
    private String password;
}
