package unit.capstone.repository.likemovie;

import org.springframework.data.jpa.repository.JpaRepository;
import unit.capstone.domain.LikeMovie;

import java.util.Optional;

public interface LikeMovieRepository extends JpaRepository<LikeMovie, Long>, LikeMovieRepositoryCustom {

    void deleteByMemberIdAndMovieId(Long memberId, Long movieId);
    Optional<LikeMovie> findByMemberIdAndMovieId(Long memberId, Long movieId);
}
