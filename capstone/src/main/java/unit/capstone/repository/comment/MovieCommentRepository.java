package unit.capstone.repository.comment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import unit.capstone.domain.MovieComment;

import java.util.List;

public interface MovieCommentRepository extends JpaRepository<MovieComment, Long> {

    List<MovieComment> findByMovieId(Long movieId);
    Page<MovieComment> findAllByMovieIdOrderByCreateDateAsc(@Param("movieId") Long movieId, Pageable pageable);

}
