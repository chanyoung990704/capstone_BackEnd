package unit.capstone.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import unit.capstone.domain.Member;
import unit.capstone.domain.Movie;
import unit.capstone.domain.RecommendedMovie;
import unit.capstone.repository.recommendedmovie.RecommendedMovieRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RecommendedMovieService {
    private final MovieService movieService;
    private final RecommendedMovieRepository recommendedMovieRepository;

    public void updateRecommendedMovies(Member member, List<Long> tmdbIds) {
        for (Long tmdbId : tmdbIds) {
            movieService.findByTmdbIdOptional(tmdbId).ifPresent(movie -> {
                // cascade 상속이 되어있어 생성자만 생성해도 커밋 시 DB 업데이트
                RecommendedMovie recommendedMovie = new RecommendedMovie(member, movie);
            });
        }
    }

    public void clearRecommendedMovieList(Member member) {
        recommendedMovieRepository.clearAllMemberRecommendedMovie(member.getId());
    }
}
