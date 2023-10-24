package unit.capstone.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import unit.capstone.domain.LikeMovies;
import unit.capstone.domain.Member;
import unit.capstone.domain.Movie;
import unit.capstone.exception.movie.DuplicateLikedMovieException;
import unit.capstone.repository.likemovie.LikeMovieRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class MovieLikeService {

    private final MemberService memberService;
    private final MovieService movieService;
    private final LikeMovieRepository likeMovieRepository;

    // else문에서 caseCade 영속성 사용
    public void movieLike(String email, Long movieId){
        Member member = memberService.findMemberByEmail(email).get();
        if(likeMovieRepository.isSavedLikeMovie(member.getId(), movieId))
            throw new DuplicateLikedMovieException("이미 좋아요 등록된 영화입니다.");
        else {
            Movie movie = movieService.findMovieById(movieId);
            new LikeMovies(member, movie);
        }


    }
}
