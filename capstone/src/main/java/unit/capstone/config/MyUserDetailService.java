package unit.capstone.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import unit.capstone.domain.Member;
import unit.capstone.service.MemberService;

@Component
@RequiredArgsConstructor
public class MyUserDetailService implements UserDetailsService {

    private final MemberService memberService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member memberByEmail = memberService.findMemberByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));

        return User.builder()
                .username(memberByEmail.getEmail())
                .password(memberByEmail.getPassword())
                .authorities(memberByEmail.getAuthority().name())
                .build();
    }
}
