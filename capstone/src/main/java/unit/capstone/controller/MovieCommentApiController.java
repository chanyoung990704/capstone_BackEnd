package unit.capstone.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import unit.capstone.controller.dto.MovieCommentDTO;
import unit.capstone.domain.Member;
import unit.capstone.domain.Movie;
import unit.capstone.domain.MovieComment;
import unit.capstone.service.MemberService;
import unit.capstone.service.MovieCommentService;
import unit.capstone.service.MovieService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MovieCommentApiController {

    private final MemberService memberService;
    private final MovieService movieService;
    private final MovieCommentService movieCommentService;

    //댓글 작성 
    @PostMapping("/api/movie/comment/{tmdbId}")
    public ResponseEntity<String> saveComment(Authentication authentication, @PathVariable Long tmdbId,
                                               @RequestBody String comment) {
        try {
            //댓글 작성에 필요한 것들 (DTO로 사용가능)
            String email = authentication.getName();
            Member member = memberService.findMemberByEmail(email).get();
            Movie movie = movieService.findByTmdbId(tmdbId);
            LocalDateTime createDate = LocalDateTime.now();

            // Rating이 필요하지 않은 생성자 사용
            MovieComment movieComment = new MovieComment(comment, createDate, member, movie);
            movieCommentService.saveComment(movieComment);

            return ResponseEntity.ok("댓글 등록 완료");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 문제로 댓글 등록 실패");
        }


    }

    //댓글 가져오기 초기 버젼이라 쓰지 않습니다.
    @GetMapping("/default/api/movie/comment/{tmdbId}")
    public ResponseEntity<List<String>> getCommentsByMovieId(@PathVariable Long tmdbId) {
        // Movie 객체에 tmdbID와 movieID(PK) 지정되어있음
        Movie byTmdbId = movieService.findByTmdbId(tmdbId);
        List<MovieComment> comments = movieCommentService.getCommentByMovieId(byTmdbId.getId());
        // stream 사용해 댓글 가져오기
        List<String> collect = comments.stream()
                .map(movieComment -> movieComment.getComment())
                .collect(Collectors.toList());
        if (comments.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(collect);
    }


    //페이징 댓글 가져오기
    @GetMapping("/api/movie/comment/{tmdbId}")
    public ResponseEntity<MovieCommentDTO> listCommentsByMovie(@PathVariable Long tmdbId,
                                                  @RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "10") int size) {
        Long movieId = movieService.findByTmdbId(tmdbId).getId();
        Pageable pageable = PageRequest.of(page, size);
        Page<MovieComment> movieCommentByPagingDateAsc = movieCommentService
                .getMovieCommentByPagingDateAsc(movieId, pageable);


        List<String> comments = movieCommentByPagingDateAsc.getContent()
                .stream()
                .map(MovieComment::getComment)
                .collect(Collectors.toList());

        if (comments.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            MovieCommentDTO movieCommentDTO = new MovieCommentDTO(comments, movieCommentByPagingDateAsc.getTotalPages());
            return ResponseEntity.ok(movieCommentDTO);
        }

    }


}
