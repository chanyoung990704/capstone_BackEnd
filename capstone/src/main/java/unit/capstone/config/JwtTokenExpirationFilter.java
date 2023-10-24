/*
package unit.capstone.config;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtTokenExpirationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 현재 사용자의 인증 정보 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // 인증이 있는 경우 (토큰이 아직 만료되지 않았음)
        if (authentication != null) {
            // 여기에서 추가적인 로직을 수행할 수 있습니다.
            // 예를 들어, 토큰의 만료 시간을 확인하고 만료되었으면 권한 차단을 수행할 수 있습니다.
            // 이 필터를 사용하여 특정 권한 또는 토큰의 만료 여부에 따라 권한을 차단할 수 있습니다.

            // 만료 로직 예시:
            // if (tokenIsExpired(authentication)) {
            //     // 토큰이 만료되었을 때 권한 차단을 수행합니다.
            //     response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            //     return;            // }
        }

        // 토큰이 만료되지 않았거나 인증이 없는 경우, 다음 필터로 이동
        filterChain.doFilter(request, response);
    }

    // 토큰 만료 여부를 확인하는 로직을 구현할 수 있습니다.
    private boolean tokenIsExpired(Authentication authentication) {



    }
}
*/
