package unit.capstone.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import unit.capstone.exception.movie.DuplicateLikedMovieException;
import unit.capstone.exception.movie.NotFoundMovieException;
import unit.capstone.service.LikeMovieService;

@RestController
@RequiredArgsConstructor
public class LikeMovieApiController {

    private final LikeMovieService likeMovieService;


    // 영화 좋아요 눌렀을 시 작동
    @PostMapping("/api/movie/like/{movieid}")
    public ResponseEntity<String> movieLike(Authentication authentication, @PathVariable Long movieid) {

        try {
            String email = authentication.getName();
            likeMovieService.movieLike(email, movieid);
            return ResponseEntity.ok("좋아요 등록 완료");
        } catch (NotFoundMovieException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (DuplicateLikedMovieException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

}
