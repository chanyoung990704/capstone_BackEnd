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
        Member memberByEmail = memberService.findMemberByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("해당 이메일의 회원이 없습니다: " + username));

        return User.builder()
                .username(memberByEmail.getEmail())
                .password(memberByEmail.getPassword())
                .authorities(memberByEmail.getAuthority().name())
                .build();
    }


}
