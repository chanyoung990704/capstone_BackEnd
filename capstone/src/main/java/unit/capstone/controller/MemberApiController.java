package unit.capstone.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import unit.capstone.controller.dto.CreateMemberDTO;
import unit.capstone.domain.Member;
import unit.capstone.domain.MemberAuthority;
import unit.capstone.service.MemberService;

@RestController
public class MemberApiController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    public MemberApiController(MemberService memberService, PasswordEncoder passwordEncoder) {
        this.memberService = memberService;
        this.passwordEncoder = passwordEncoder;
    }


    @PostMapping("/api/register")
    public ResponseEntity<String> registerMember(@RequestBody @Valid CreateMemberDTO createMemberDTO) {

        try {
            Member member = new Member(createMemberDTO.getName(), passwordEncoder.encode(createMemberDTO.getPassword()), createMemberDTO.getEmail(), MemberAuthority.USER);
            memberService.registerMember(member);
            return ResponseEntity.ok("회원가입 완료");
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("회원가입 중 오류");
        }

    }




}
