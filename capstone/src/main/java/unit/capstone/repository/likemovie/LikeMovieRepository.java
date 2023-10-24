package unit.capstone.repository.likemovie;

import org.springframework.data.jpa.repository.JpaRepository;
import unit.capstone.domain.LikeMovies;

public interface LikeMovieRepository extends JpaRepository<LikeMovies, Long>, LikeMovieRepositoryCustom {
}
