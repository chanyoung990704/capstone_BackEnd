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
import unit.capstone.controller.dto.MemberDTO;
import unit.capstone.domain.*;
import unit.capstone.service.MemberService;
import unit.capstone.service.MovieService;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/api/register")
    public ResponseEntity<?> registerMember(@RequestBody @Valid CreateMemberDTO createMemberDTO) {
        try {
            Member member = new Member(createMemberDTO.getName(),
                    passwordEncoder.encode(createMemberDTO.getPassword()),
                    createMemberDTO.getEmail(),
                    MemberAuthority.ROLE_USER);
            memberService.registerMember(member);
            return ResponseEntity.ok("회원가입 완료");
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("email", "이미 존재하는 이메일입니다"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("회원가입 중 오류 발생");
        }
    }

    // 멤버 찾기 기능 테스트용 일단은 사용하지 않음
    @GetMapping("/api/members")
    public ResponseEntity<List<MemberDTO>> findMembers() {
        List<MemberDTO> members = memberService.findMembers().stream()
                .map(member -> new MemberDTO(member.getUsername(), member.getEmail()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(members);
    }

    @GetMapping("/api/member/likemovie")
    public ResponseEntity<List<Long>> findLikeMovie(Authentication authentication) {
        return memberService.findMemberByEmail(authentication.getName())
                .map(member -> member.getLikeMovies().stream()
                        .map(likeMovie -> likeMovie.getMovie().getTmdbId())
                        .collect(Collectors.toList()))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/api/member/recommendedmovie")
    public ResponseEntity<List<Long>> findRecommendedMovie(Authentication authentication) {
        return memberService.findMemberByEmail(authentication.getName())
                .map(member -> member.getRecommendedMovies().stream()
                        .map(recommendedMovie -> recommendedMovie.getMovie().getTmdbId())
                        .collect(Collectors.toList()))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // 특정 권한별 컨트롤러 작동하는지 테스트하는 메서드
    @GetMapping("/api/test")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")
    public ResponseEntity<String> detailAuth(Authentication authentication) {
        return ResponseEntity.ok("관리자 접근 승인: " + authentication.getName());
    }
}
