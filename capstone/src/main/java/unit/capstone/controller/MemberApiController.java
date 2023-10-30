package unit.capstone.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import unit.capstone.controller.dto.CreateMemberDTO;
import unit.capstone.domain.Member;
import unit.capstone.domain.MemberAuthority;
import unit.capstone.domain.Movie;
import unit.capstone.service.MemberService;
import unit.capstone.service.MovieService;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;




    // 회원가입
    @PostMapping("/api/register")
    public ResponseEntity<String> registerMember(@RequestBody @Valid CreateMemberDTO createMemberDTO) {

        try {
            Member member = new Member(createMemberDTO.getName(), passwordEncoder.encode(createMemberDTO.getPassword()),
                    createMemberDTO.getEmail(), MemberAuthority.ROLE_USER);
            memberService.registerMember(member);
            return ResponseEntity.ok("회원가입 완료");
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("회원가입 중 오류");
        }

    }

    // 멤버 반환 DTO 사용해야
    @GetMapping("/api/Members")
    public List<Member> findMembers() {
        return memberService.findMembers();
    }



    // 접근 권한 설정 (Test용)

    @GetMapping("/api/test")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")
    public String detailAuth(Authentication authentication) {
        System.out.println("authentication = " + authentication);
        System.out.println("authentication.getName() = " + authentication.getName());
        System.out.println("authentication = " + authentication.getAuthorities());
        return "ok";
    }


    
}
