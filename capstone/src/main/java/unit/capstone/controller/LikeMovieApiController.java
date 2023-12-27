package unit.capstone.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import unit.capstone.domain.Member;
import unit.capstone.exception.movie.DuplicateLikedMovieException;
import unit.capstone.exception.movie.NotFoundMovieException;
import unit.capstone.exception.movie.NotFoundMovieLikeException;
import unit.capstone.service.LikeMovieService;
import unit.capstone.service.MemberService;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class LikeMovieApiController {

    private final LikeMovieService likeMovieService;
    private final MemberService memberService;

    @PostMapping("/api/movies/{tmdbId}/likes")
    public ResponseEntity<String> movieLike(Authentication authentication, @PathVariable Long tmdbId) {
        return processLikeOperation(authentication, tmdbId, true);
    }

    @DeleteMapping("/api/movies/{tmdbId}/likes")
    public ResponseEntity<String> cancelMovieLike(Authentication authentication, @PathVariable Long tmdbId) {
        return processLikeOperation(authentication, tmdbId, false);
    }

    private ResponseEntity<String> processLikeOperation(Authentication authentication, Long tmdbId, boolean isLike) {
        try {
            String email = authentication.getName();
            Member member = memberService.findMemberByEmail(email)
                    .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

            if (isLike) {
                likeMovieService.movieLike(member, tmdbId);
                return ResponseEntity.ok("좋아요 등록 완료");
            } else {
                likeMovieService.cancelMovieLike(member, tmdbId);
                return ResponseEntity.ok("좋아요 취소 완료");
            }
        } catch (NotFoundMovieException | NotFoundMovieLikeException | DuplicateLikedMovieException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 오류로 요청을 처리할 수 없습니다.");
        }
    }
}
