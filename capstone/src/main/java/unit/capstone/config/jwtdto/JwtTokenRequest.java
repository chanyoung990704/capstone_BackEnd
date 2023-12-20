package unit.capstone.config.jwtdto;

import lombok.Data;

@Data
public class JwtTokenRequest{
    private String email;
    private String password;
}