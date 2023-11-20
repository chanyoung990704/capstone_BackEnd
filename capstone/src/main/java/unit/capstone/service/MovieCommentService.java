package unit.capstone.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import unit.capstone.domain.MovieComment;
import unit.capstone.repository.comment.MovieCommentRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MovieCommentService {

    private final MovieCommentRepository movieCommentRepository;

    public void saveComment(MovieComment movieComment) {
        movieCommentRepository.save(movieComment);
    }

    //tmdbId가 아닌 movieId로 조회하는 메서드
    @Transactional(readOnly = true)
    public List<MovieComment> getCommentByMovieId(Long movieId) {
        return movieCommentRepository.findByMovieId(movieId);
    }


    //댓글 10개 페이징 & 날짜 정렬
    @Transactional(readOnly = true)
    public Page<MovieComment> getMovieCommentByPagingDateAsc(Long movieId, Pageable pageable) {
        return movieCommentRepository.findAllByMovieIdOrderByCreateDateAsc(movieId, pageable);
    }
}
