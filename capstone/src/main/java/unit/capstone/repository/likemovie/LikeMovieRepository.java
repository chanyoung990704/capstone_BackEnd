package unit.capstone.repository.likemovie;

import org.springframework.data.jpa.repository.JpaRepository;
import unit.capstone.domain.LikeMovies;

import java.util.Optional;

public interface LikeMovieRepository extends JpaRepository<LikeMovies, Long>, LikeMovieRepositoryCustom {

    void deleteByMemberIdAndMovieId(Long memberId, Long movieId);

    Optional<LikeMovies> findByMemberIdAndMovieId(Long memberId, Long movieId);


}
