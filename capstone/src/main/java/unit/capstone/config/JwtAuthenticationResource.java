package unit.capstone.config;

import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtAuthenticationResource {

    private final JwtTokenService tokenService;
    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationResource(JwtTokenService tokenService, AuthenticationManager authenticationManager) {
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
    }


    @PostMapping("/authenticate")
    public ResponseEntity<JwtTokenResponse> generateToken(@RequestBody JwtTokenRequest jwtTokenRequest) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(jwtTokenRequest.getEmail(), jwtTokenRequest.getPassword());
        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        String token = tokenService.createToken(authenticate);
        return ResponseEntity.ok(new JwtTokenResponse(token));
    }




    @Data
    static class JwtTokenRequest{
        private String email;
        private String password;
    }

    @Data
    static class JwtTokenResponse{
        private String token;

        public JwtTokenResponse() {
        }

        public JwtTokenResponse(String token) {
            this.token = token;
        }
    }


}