package unit.capstone.repository.recommendedmovie;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import unit.capstone.domain.QRecommendedMovie;

@Repository
public class RecommendedMovieRepositoryImpl implements RecommendedMovieRepositoryCustom{

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public RecommendedMovieRepositoryImpl(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public void clearAllMemberRecommendedMovie(Long memberId) {
        QRecommendedMovie recommendedMovie = QRecommendedMovie.recommendedMovie;
        queryFactory.delete(recommendedMovie)
                .where(recommendedMovie.member.id.eq(memberId))
                .execute();
    }
}
