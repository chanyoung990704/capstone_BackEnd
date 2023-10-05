package unit.capstone.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import unit.capstone.domain.Member;
import unit.capstone.domain.Movie;
import unit.capstone.exception.member.NotFoundMemberException;
import unit.capstone.service.MemberService;
import unit.capstone.service.MovieService;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class MovieLikeApiController {

    private final MemberService memberService;
    private final MovieService movieService;


    @PostMapping("/api/movie/{movieid}")
    public ResponseEntity<String> movieLike(Authentication authentication, @PathVariable Long movieid) {

        try {
            String email = authentication.getName();
            // 어차피 인증된 토큰으로 진행하기 때문에 Email검증은 없어도 될 거 같음
            // service에서 Member Class를 갖는 메서드 따로 정의하자.
            Optional<Member> memberByEmail = memberService.findMemberByEmail(authentication.getName());
            Member member = memberByEmail.orElseThrow(() -> new NotFoundMemberException("맞는 Email을 가진 유저가 없습니다"));
            Movie movie = movieService.findMovieById(movieid);

            memberService.movieLike(member, movie);
            return ResponseEntity.ok("좋아요 등록 완료");
        } catch (NotFoundMemberException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

}
