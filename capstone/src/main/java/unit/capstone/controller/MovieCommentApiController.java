package unit.capstone.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import unit.capstone.domain.Member;
import unit.capstone.domain.Movie;
import unit.capstone.domain.MovieComment;
import unit.capstone.service.MemberService;
import unit.capstone.service.MovieCommentService;
import unit.capstone.service.MovieService;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class MovieCommentApiController {

    private final MemberService memberService;
    private final MovieService movieService;
    private final MovieCommentService movieCommentService;


    @PostMapping("/api/movie/comment/{movieId}")
    public ResponseEntity<String> movieComment(Authentication authentication, @PathVariable Long movieId,
                                               @RequestBody String comment) {
        try {
            String email = authentication.getName();
            Member member = memberService.findMemberByEmail(email).get();
            Movie movie = movieService.findMovieById(movieId);
            LocalDateTime createDate = LocalDateTime.now();

            MovieComment movieComment = new MovieComment(comment, createDate, member, movie);
            movieCommentService.saveComment(movieComment);

            return ResponseEntity.ok("댓글 등록 완료");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 문제로 댓글 등록 실패");
        }


    }




}
