package unit.capstone.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import unit.capstone.domain.LikeMovies;
import unit.capstone.domain.Member;
import unit.capstone.domain.Movie;
import unit.capstone.domain.RecommendedMovie;
import unit.capstone.exception.movie.DuplicateLikedMovieException;
import unit.capstone.repository.recommendedmovie.RecommendedMovieRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RecommendedMovieService {
    private final MemberService memberService;
    private final MovieService movieService;
    private final RecommendedMovieRepository recommendedMovieRepository;

    public void updateRecommendedMovies(Member member, List<Long> tmdbIds) {

        for (Long tmdbId : tmdbIds) {
            // Movie 엔티티의 PK값이 tmdbID가 아니기에 엔티티 연결을 위해 서치
            Movie movie = movieService.findByTmdbId(tmdbId);

            // 여기서 RecommendedMovie 객체를 생성하고, 필요한 처리를 수행합니다.
            // 예: 데이터베이스에 저장, 연관 관계 설정 등
            RecommendedMovie recommendedMovie = new RecommendedMovie(member, movie);

            // 추천된 영화를 저장하거나 업데이트하는 로직
            // 예: recommendedMovieService.save(recommendedMovie);
        }
    }

    public void clearRecommendedMovieList(Member member){
        recommendedMovieRepository.clearAllMemberRecommendedMovie(member.getId());
    }


}
