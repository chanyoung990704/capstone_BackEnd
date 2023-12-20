package unit.capstone.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import unit.capstone.config.jwtdto.JwtTokenRequest;
import unit.capstone.config.jwtdto.JwtTokenResponse;
import unit.capstone.service.JwtTokenService;

@RestController
@RequiredArgsConstructor
public class JwtAuthenticationResource {

    private final JwtTokenService tokenService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/authenticate")
    public ResponseEntity<JwtTokenResponse> generateToken(@RequestBody JwtTokenRequest jwtTokenRequest) {
        try {
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
                    jwtTokenRequest.getEmail(), jwtTokenRequest.getPassword()
            );
            Authentication authentication = authenticationManager.authenticate(authRequest);
            String token = tokenService.createToken(authentication);
            return ResponseEntity.ok(new JwtTokenResponse(token));
        } catch (AuthenticationException e) {
            // 적절한 예외 처리 (로그 기록, 사용자에게 에러 메시지 전달 등)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
