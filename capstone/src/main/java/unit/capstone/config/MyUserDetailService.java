package unit.capstone.config;


import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import unit.capstone.domain.Member;
import unit.capstone.service.MemberService;

import java.util.Optional;

@Component
public class MyUserDetailService implements UserDetailsService {

    private final MemberService memberService;


    public MyUserDetailService(MemberService memberService) {
        this.memberService = memberService;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Member> isMember = memberService.findMemberByEmail(username);
        Member memberByEmail = isMember.orElseThrow(() -> new UsernameNotFoundException("없는 회원"));

        return User.builder()
                .username(memberByEmail.getEmail())
                .password(memberByEmail.getPassword())
                .authorities(memberByEmail.getAuthority().name())
                .build();
    }
}
