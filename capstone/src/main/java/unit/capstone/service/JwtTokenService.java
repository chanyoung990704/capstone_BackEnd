package unit.capstone.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JwtTokenService {

    private static final String ISSUER = "self";
    private static final long TOKEN_EXPIRATION_SECONDS = 60 * 15; // 15분

    private final JwtEncoder jwtEncoder;

    public String createToken(Authentication authentication) {
        JwtClaimsSet claims = buildJwtClaimsSet(authentication);
        JwtEncoderParameters parameters = JwtEncoderParameters.from(claims);
        return jwtEncoder.encode(parameters).getTokenValue();
    }

    private JwtClaimsSet buildJwtClaimsSet(Authentication authentication) {
        return JwtClaimsSet.builder()
                .issuer(ISSUER)
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plusSeconds(TOKEN_EXPIRATION_SECONDS))
                .subject(authentication.getName())
                .claim("scope", createScope(authentication))
                .build();
    }

    private String createScope(Authentication authentication) {
        return authentication.getAuthorities().stream()
                .map(a -> a.getAuthority())
                .collect(Collectors.joining(" "));
    }
}
