package unit.capstone.config.jwtdto;

import lombok.Data;

@Data
public class JwtTokenResponse {

    private String token;
    public JwtTokenResponse() {
    }
    public JwtTokenResponse(String token) {
        this.token = token;
    }
}