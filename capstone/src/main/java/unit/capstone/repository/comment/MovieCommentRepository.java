package unit.capstone.repository.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import unit.capstone.domain.MovieComment;

public interface MovieCommentRepository extends JpaRepository<MovieComment, Long> {
}
