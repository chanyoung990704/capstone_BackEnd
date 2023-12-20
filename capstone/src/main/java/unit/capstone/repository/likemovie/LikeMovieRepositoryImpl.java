package unit.capstone.repository.likemovie;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import unit.capstone.domain.LikeMovie;
import unit.capstone.domain.QLikeMovie;

import java.util.List;

@Repository
public class LikeMovieRepositoryImpl implements LikeMovieRepositoryCustom{

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public LikeMovieRepositoryImpl(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    QLikeMovie likeMovies = QLikeMovie.likeMovie;

    // 이미 좋아요한 영화 리스트에 있는지
    // tmdbId를 이용하여 서치
    @Override
    public boolean isSavedLikeMovie(Long memberId, Long tmdbId) {
        JPAQuery<LikeMovie> query = queryFactory.selectFrom(likeMovies)
                .where(likeMovies.member.id.eq(memberId).and(likeMovies.movie.tmdbId.eq(tmdbId)));
        List<LikeMovie> result = query.fetch();

        if(!result.isEmpty())
            return true;
        else
            return false;
    }
}
