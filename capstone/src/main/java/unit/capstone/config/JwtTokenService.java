package unit.capstone.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.stream.Collectors;

@Service
public class JwtTokenService {

    private final JwtEncoder jwtEncoder;

    public JwtTokenService(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }

    public String createToken(Authentication authentication) {
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plusSeconds(60 * 15)) //토큰 만료 시간
                .subject(authentication.getName())
                .claim("scope", createScope(authentication))
                .build();

        JwtEncoderParameters parameters = JwtEncoderParameters.from(claims);
        return jwtEncoder.encode(parameters).getTokenValue();
    }

    public String createScope(Authentication authentication) {
        return authentication.getAuthorities().stream()
                .map(a -> a.getAuthority())
                .collect(Collectors.joining(" "));
    }

}
