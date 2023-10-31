package unit.capstone.repository.recommendedmovie;

import org.springframework.data.jpa.repository.JpaRepository;
import unit.capstone.domain.RecommendedMovie;

public interface RecommendedMovieRepository extends JpaRepository<RecommendedMovie, Long> {

}
